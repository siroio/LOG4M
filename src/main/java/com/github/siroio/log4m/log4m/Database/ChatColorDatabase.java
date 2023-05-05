package com.github.siroio.log4m.log4m.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ChatColorDatabase {

    private final HikariDataSource dataSource;
    private final String DEFAULT_COLOR_KEY = "default_color";
    private final String HIGHLIGHT_COLOR_KEY = "highlight_color";

    public ChatColorDatabase(String dbPath) {

        // DBファイル生成
        Path path = Paths.get(dbPath);
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 接続設定
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + dbPath);
        config.setConnectionInitSql("SELECT 1");
        config.setMaximumPoolSize(20); // コネクションプールのサイズを20に設定

        // コネクションプールを作成
        dataSource = new HikariDataSource(config);
        initialize();
    }

    private void initialize() {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS uuid_colors (uuid TEXT PRIMARY KEY, default_color TEXT, highlight_color TEXT);")) {
                statement.executeUpdate();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void Close() {
        if (dataSource != null)
            dataSource.close();
    }

    public void initializeChatColor(Player player, ChatColor defaultColor, ChatColor highlightColor)
    {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT OR IGNORE INTO uuid_colors (uuid, default_color, highlight_color) VALUES (?, ?, ?)")) {
            stmt.setString(1, player.getUniqueId().toString());
            stmt.setString(2, defaultColor.getName());
            stmt.setString(3, highlightColor.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChatColor getDefaultColor(Player player) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT default_color FROM uuid_colors WHERE uuid = ?")) {
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return ChatColor.of(rs.getString(DEFAULT_COLOR_KEY));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChatColor getHighlightColor(Player player) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT highlight_color FROM uuid_colors WHERE uuid = ?")) {
            stmt.setString(1, player.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return ChatColor.of(rs.getString(HIGHLIGHT_COLOR_KEY));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void upsertDefaultColor(Player player, String defaultColor) {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE uuid_colors SET default_color = ? WHERE uuid = ?")) {
            stmt.setString(1, defaultColor);
            stmt.setString(2, player.getUniqueId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upsertHighlightColor(Player player, String highlightColor) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE uuid_colors SET highlight_color = ? WHERE uuid = ?")) {
            stmt.setString(1, highlightColor);
            stmt.setString(2, player.getUniqueId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
