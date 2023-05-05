package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.PlayerList.HighlightList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ObserveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length < 1) return ErrorMessage(sender);
        Player player = Bukkit.getServer().getPlayerExact(args[0]);
        if(player == null) return ErrorMessage(sender);
        HighlightList.Add(player);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList() : List.of();
    }

    private boolean ErrorMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.RED + "Unknown Player");
        return false;
    }
}
