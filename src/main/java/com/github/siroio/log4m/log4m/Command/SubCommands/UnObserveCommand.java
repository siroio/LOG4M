package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.PlayerList.HighlightList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class UnObserveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length < 1) return ErrorMessage(sender);
        Player player = HighlightList.getPlayer(args[0]);
        if(player == null) return ErrorMessage(sender);
        HighlightList.Remove(player);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            List<String> names = HighlightList.getPlayersToString();
            return names.isEmpty() ? List.of() : names;
        }
        return List.of();
    }

    private boolean ErrorMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.RED + "Unknown Player");
        return false;
    }
}
