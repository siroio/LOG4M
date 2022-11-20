package com.github.siroio.log4m.log4m.Command;

import com.github.siroio.log4m.log4m.Command.SubCommands.HighlightCommand;
import com.github.siroio.log4m.log4m.Command.SubCommands.RemoveHighlightCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class CommandExec implements CommandExecutor, TabCompleter {

    public Map<String, CommandExecutor> subCommand;

    public CommandExec() {
        Map<String, CommandExecutor> commands = new HashMap<>();
        commands.put("look", new HighlightCommand());
        commands.put("unlook", new RemoveHighlightCommand());
        subCommand = Collections.unmodifiableMap(commands);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("log4m.op")) return false;
        String arg = "NO ARGS";
        if(args.length > 0) {
            String cmd = args[0].toLowerCase(Locale.ENGLISH);
            if(subCommand.containsKey(cmd)) arg = cmd;
        }
        if(arg.equals("NO ARGS")) return false;
        return subCommand.get(arg).onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return new ArrayList<>(subCommand.keySet());
        }

        return null;
    }
}
