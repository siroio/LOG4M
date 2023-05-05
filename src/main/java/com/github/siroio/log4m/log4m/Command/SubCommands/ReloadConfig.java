package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.ChatColorManager;
import com.github.siroio.log4m.log4m.Log4m;
import com.github.siroio.log4m.log4m.Utility.ChatColorUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Log4m.Config().Load();
        return false;
    }
}
