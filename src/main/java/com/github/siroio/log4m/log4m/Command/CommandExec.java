package com.github.siroio.log4m.log4m.Command;

import com.github.siroio.log4m.log4m.Command.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class CommandExec implements CommandExecutor, TabCompleter {


    public CommandExec() {
        CommandManager.getInstance().RegisterCommand("Observe", new ObserveCommand());
        CommandManager.getInstance().RegisterCommand("UnObserve", new UnObserveCommand());
        CommandManager.getInstance().RegisterCommand("DefaultColor", new SetDefaultChatColor());
        CommandManager.getInstance().RegisterCommand("HighlightColor", new SetHighlightChatColor());
        CommandManager.getInstance().RegisterCommand("ResetColor", new ResetColor());
        CommandManager.getInstance().RegisterCommand("ReloadConfig", new ReloadConfig());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("log4m.op")) return false;
        return CommandManager.getInstance().ExecuteCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!sender.hasPermission("log4m.op")) return List.of();
        return CommandManager.getInstance().TabComplete(sender, command, alias, args);
    }
}
