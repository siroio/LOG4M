package com.github.siroio.log4m.log4m;

import com.github.siroio.log4m.log4m.Events.abstracts.EventListener;
import org.bukkit.plugin.Plugin;

public class EventRegister {

    private static final EventRegister register = new EventRegister();
    private EventRegister(){}
    public static EventRegister getInstance() { return register; }

    public void Register(EventListener event, Plugin plugin) {
        if(plugin == null || event == null) return;
        plugin.getServer().getPluginManager().registerEvents(event, plugin);
    }
}
