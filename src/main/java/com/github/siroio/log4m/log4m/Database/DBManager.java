package com.github.siroio.log4m.log4m.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class DBManager {

    private HikariDataSource dataSource;

    // constructor
    public DBManager(String dbpath) {

        // dbファイル生成
        if (!Files.exists(Paths.get(dbpath))) {
            try { Files.createFile(Paths.get(dbpath)); }
            catch (IOException e) { e.printStackTrace(); }
        }

        // 設定生成
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:" + dbpath);
        config.setConnectionInitSql("SELECT 1");

        // 設定を使って接続
        dataSource = new HikariDataSource(config);
    }

    public void Close() {
        if (dataSource != null)
            dataSource.close();
    }
}
