package com.github.siroio.log4m.log4m;

import com.github.siroio.log4m.log4m.Command.CommandExec;
import com.github.siroio.log4m.log4m.Command.CommandManager;
import com.github.siroio.log4m.log4m.Config.Config;
import com.github.siroio.log4m.log4m.Database.ChatColorDatabase;
import com.github.siroio.log4m.log4m.Events.OnExecuteCommand;
import com.github.siroio.log4m.log4m.Events.OnPlayerJoin;
import com.github.siroio.log4m.log4m.Events.UnknownCommandMessage;
import com.github.siroio.log4m.log4m.PlayerList.HighlightList;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Log4m extends JavaPlugin {

    public static final String Prefix = "LOG4M";
    private static Config config;
    private static ChatColorDatabase manager;

    @Override
    public void onEnable() {
        // create config
        config = new Config(this);
        // add event
        new OnExecuteCommand(this);
        new UnknownCommandMessage(this);
        new OnPlayerJoin(this);
        // command register
        Objects.requireNonNull(getCommand("log4m")).setExecutor(new CommandExec());
        // connect db
        manager = new ChatColorDatabase(getDataFolder().getAbsolutePath() + "/PlayerCnf.db");
    }

    @Override
    public void onDisable() {
        manager.Close();
        HighlightList.Clear();
        HandlerList.unregisterAll(this);
    }

    public static Config Config() { return config; }
    public static ChatColorDatabase GetDB() { return manager; }
}
