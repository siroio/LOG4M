package com.github.siroio.log4m.log4m.Events;

import com.github.siroio.log4m.log4m.ChatColorManager;
import com.github.siroio.log4m.log4m.Events.abstracts.EventListener;
import com.github.siroio.log4m.log4m.Log4m;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayerJoin extends EventListener {

    public OnPlayerJoin(Plugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void OnPlayerJoinHandler(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if(!player.hasPermission("log4m.op") || !player.isOp()) return;

        ChatColor defaultColor = ChatColor.of(Log4m.Config().getColor());
        ChatColor highlight = ChatColor.of(Log4m.Config().getHLColor());

        Log4m.GetDB().initializeChatColor(player, defaultColor, highlight);

        ChatColorManager.SetDefaultColor(player, Log4m.GetDB().getDefaultColor(player).getName());
        ChatColorManager.SetHighlightColor(player, Log4m.GetDB().getHighlightColor(player).getName());
    }
}
