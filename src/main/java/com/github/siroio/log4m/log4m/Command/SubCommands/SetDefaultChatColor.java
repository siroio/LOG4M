package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.ChatColorManager;
import com.github.siroio.log4m.log4m.Utility.ChatColorUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SetDefaultChatColor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args == null || args.length < 1) return ErrorMessage(sender);
        String color = ChatColorUtility.validateColorCode(args[0]);
        if(color == null) return ErrorMessage(sender);
        return ChatColorManager.SetDefaultColor((Player) sender, color);
    }

    private boolean ErrorMessage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.RED + "Unknown Color");
        return false;
    }
}
