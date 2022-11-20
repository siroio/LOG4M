package com.github.siroio.log4m.log4m;

import com.github.siroio.log4m.log4m.Command.CommandExec;
import com.github.siroio.log4m.log4m.Config.Config;
import com.github.siroio.log4m.log4m.Database.DBManager;
import com.github.siroio.log4m.log4m.Events.OnExecuteCommand;
import com.github.siroio.log4m.log4m.Events.UnknownCommandMessage;
import com.github.siroio.log4m.log4m.PlayerList.HighlightList;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Log4m extends JavaPlugin {

    public static final String Prefix = "LOG4M";
    private Config config;
    private DBManager manager;

    @Override
    public void onEnable() {
        // create config
        config = new Config(this);
        // add event
        new OnExecuteCommand(this, this);
        new UnknownCommandMessage(this, this);
        // command register
        getCommand("log4m").setExecutor(new CommandExec());
        // connect db
        manager = new DBManager("PlayerCnf.db");
        getServer().getLogger().info(ChatColor.GREEN + "Enable Log4m");
    }

    @Override
    public void onDisable() {
        HighlightList.Clear();
        HandlerList.unregisterAll(this);
        getServer().getLogger().info(ChatColor.RED + "Disable Log4m");
    }

    public Config Config() { return config; }
    public DBManager GetDB() { return manager; }
}
