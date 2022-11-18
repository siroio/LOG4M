package com.github.siroio.log4m.log4m.Config;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;


public class Config {

    private final Plugin plugin;
    private FileConfiguration config;

    private String Message;
    public String getMessage() { return Message; }
    private String Color;
    public String getColor() { return Color; }
    private String HLcolor;
    public String getHLcolor() { return HLcolor; }

    public Config(Plugin plugin) {
        this.plugin = plugin;
        Load();
    }

    public void Load() {
        plugin.saveDefaultConfig();
        if(config != null) plugin.reloadConfig();
        config = plugin.getConfig();
        Color = config.getString("color");
        Message = config.getString("message");
        HLcolor = config.getString("highlight");
    }

}
