package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.PlayerList.HighlightList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HighlightCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        for (Player p: Bukkit.getServer().getOnlinePlayers()) {
            if(p.getName().equalsIgnoreCase(args[1])) player = p;
        }
        if(player == null) return false;
        HighlightList.Add(player);
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 0) {
            List<String> playerName = new ArrayList<>();
            for (Player p : Bukkit.getServer().getOnlinePlayers())
                playerName.add(p.getName());
            return playerName;
        }

        return null;
    }
}
