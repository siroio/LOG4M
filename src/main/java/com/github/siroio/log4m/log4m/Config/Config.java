package com.github.siroio.log4m.log4m.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;


public class Config {

    private final Plugin plugin;
    private FileConfiguration config;

    private String Message;
    public String getMessage() { return Message; }
    private String Color;
    public String getColor() { return Color; }
    private String HLColor;
    public String getHLColor() { return HLColor; }

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
        HLColor = config.getString("highlight");
    }

}
