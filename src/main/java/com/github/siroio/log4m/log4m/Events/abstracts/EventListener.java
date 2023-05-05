package com.github.siroio.log4m.log4m.Events.abstracts;

import com.github.siroio.log4m.log4m.EventRegister;
import com.github.siroio.log4m.log4m.Log4m;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public abstract class EventListener implements Listener {

    protected enum SENDMODE { ALWAYS, OP, PERM }
    protected final Plugin plugin;

    public EventListener(Plugin plugin) {
        this.plugin = plugin;
        EventRegister.getInstance().Register(this, plugin);
    }

    public void SendMessage(String msg, SENDMODE mode, String permission) {
        plugin.getServer().getOnlinePlayers().stream()
                .filter(p -> switch (mode) {
                    case ALWAYS -> true;
                    case OP -> p.isOp();
                    case PERM -> p.hasPermission(permission);
                }).forEach(p -> p.sendMessage(msg));
    }
    public void SendMessage(String msg, SENDMODE mode) {
        SendMessage(msg, mode, "");
    }

    public void SendMessage(SENDMODE mode, String permission, BaseComponent... components) {
        plugin.getServer().getOnlinePlayers().stream()
                .filter(p -> switch (mode) {
                    case ALWAYS -> true;
                    case OP -> p.isOp();
                    case PERM -> p.hasPermission(permission);
                }).forEach(p -> p.spigot().sendMessage(components));

    }

    public void SendMessage(SENDMODE mode, BaseComponent... components) {
        SendMessage(mode, "", components);
    }
}
