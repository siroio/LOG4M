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
    protected final Log4m log;

    public EventListener(Plugin plugin, Log4m log) {
        this.plugin = plugin;
        this.log = log;
        EventRegister.getInstance().Register(this, plugin);
    }

    public void SendMessage(String msg, SENDMODE mode, String permission) {
        if(mode == SENDMODE.ALWAYS) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                p.sendMessage(msg);
            }
        }

        else if(mode == SENDMODE.OP) {
            for (Player p : plugin.getServer().getOnlinePlayers()) {
                if(p.isOp()) p.sendMessage(msg);
            }
        }

        else if(mode == SENDMODE.PERM) {
            for(Player p : plugin.getServer().getOnlinePlayers()) {
                if(p.hasPermission(permission)) p.sendMessage(msg);
            }
        }
    }

    public void SendMessage(SENDMODE mode, BaseComponent... components) {
        if(mode == SENDMODE.ALWAYS) {
            for (Player p : plugin.getServer().getOnlinePlayers())
                p.spigot().sendMessage(components);
        }

        else if (mode == SENDMODE.OP) {
            for (Player p : plugin.getServer().getOnlinePlayers())
                if(p.isOp()) p.spigot().sendMessage(components);
        }
    }
}
