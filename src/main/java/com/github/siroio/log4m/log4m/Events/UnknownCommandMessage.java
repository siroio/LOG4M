package com.github.siroio.log4m.log4m.Events;


import com.github.siroio.log4m.log4m.Events.abstracts.EventListener;
import com.github.siroio.log4m.log4m.Log4m;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;

public class UnknownCommandMessage extends EventListener {

    public UnknownCommandMessage(Plugin plugin, Log4m log) {
        super(plugin, log);
    }

    @EventHandler
    public void onMessage(PlayerCommandPreprocessEvent event) {
        // Disable Unknown Command Message
        if(!event.getPlayer().isOp() || !isCancel(event.getMessage())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onConsoleMessage(ServerCommandEvent event) {
        // @ is the name of Command Block
        if(event.getSender().getName().equals("@") || !isCancel(event.getCommand())) return;
        event.setCancelled(true);
    }

    private boolean isCancel(String message) {
        String[] args = message.split(" ");
        return Bukkit.getHelpMap().getHelpTopic(args[0]) == null;
    }
}