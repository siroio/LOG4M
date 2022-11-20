package com.github.siroio.log4m.log4m.Events;

import com.github.siroio.log4m.log4m.Events.abstracts.EventListener;
import com.github.siroio.log4m.log4m.Log4m;
import com.github.siroio.log4m.log4m.PlayerList.HighlightList;
import com.github.siroio.log4m.log4m.Utility.StringUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;

public class OnExecuteCommand extends EventListener {

    public OnExecuteCommand(Plugin plugin, Log4m log) { super(plugin, log); }

    // Player
    @EventHandler
    public void OnExecCommand(PlayerCommandPreprocessEvent event) {
        Player sender = event.getPlayer();
        String cmd = event.getMessage();

        TextComponent cmp = new TextComponent(fromConfigFormat(sender.getName(), cmd));
        String HexCode = HighlightList.isHighlight(sender) ? log.Config().getHLcolor() : log.Config().getColor();
        cmp.setColor(ChatColor.of(HexCode));

        SendMessage(SENDMODE.OP, cmp);
    }

    // Non player
    @EventHandler
    public void OnExecServerCommand(ServerCommandEvent event) {
        // @ is the name of Command Block
        if(event.getSender().getName().equals("@")) return;
        CommandSender sender = event.getSender();
        String cmd = event.getCommand();

        TextComponent cmp = new TextComponent(fromConfigFormat(sender.getName(), cmd));
        cmp.setColor(ChatColor.of(log.Config().getColor()));

        SendMessage(SENDMODE.OP, cmp);
    }

    private String fromConfigFormat(String SenderName, String message) {
        String cnfMsg = log.Config().getMessage();
        cnfMsg = StringUtils.replaceByHand(cnfMsg, "$prefix", Log4m.Prefix);
        cnfMsg = StringUtils.replaceByHand(cnfMsg, "$sender", SenderName);
        cnfMsg = StringUtils.replaceByHand(cnfMsg, "$command", message);
        return cnfMsg;
    }
}
