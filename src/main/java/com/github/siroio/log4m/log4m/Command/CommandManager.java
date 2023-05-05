package com.github.siroio.log4m.log4m.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;


public class CommandManager {
    private static CommandManager instance;
    private final Map<String, CommandExecutor> commands = new HashMap<>();;

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }

        return instance;
    }

    public void RegisterCommand(String command, CommandExecutor commandExecutor) {
        commands.put(command, commandExecutor);
    }

    public boolean ExecuteCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return false;

        String subCommandName = args[0];
        CommandExecutor subCommand = commands.get(subCommandName);

        if(subCommand == null) return false;
        return subCommand.onCommand(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
    }

    public List<String> TabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return new ArrayList<>(commands.keySet());

        String subCommandName = args[0];
        CommandExecutor subCommand = commands.get(subCommandName);
        return subCommand == null ? List.of() : onTabComplete(sender, command, alias, subCommand, Arrays.copyOfRange(args, 1, args.length)) ;
    }

    private List<String> onTabComplete(CommandSender sender, Command command, String alias, CommandExecutor executor, String[] args) {
        return executor instanceof TabCompleter ? ((TabCompleter) executor).onTabComplete(sender, command, alias, args) : List.of();
    }
}
