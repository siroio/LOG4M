package com.github.siroio.log4m.log4m.Command.SubCommands;

import com.github.siroio.log4m.log4m.ChatColorManager;
import com.github.siroio.log4m.log4m.Log4m;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetColor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ChatColorManager.SetDefaultColor(player, Log4m.Config().getColor());
        ChatColorManager.SetHighlightColor(player, Log4m.Config().getHLColor());
        return false;
    }
}
