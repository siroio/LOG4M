package com.github.siroio.log4m.log4m;

import com.github.siroio.log4m.log4m.Utility.ChatColorUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChatColorManager {
    private ChatColorManager(){}

    private static final Map<Player, ChatColor> defaultColors = new HashMap<>();
    private static final Map<Player, ChatColor> highlightColor = new HashMap<>();

    public static ChatColor GetDefaultColor(Player player)
    {
        if(!defaultColors.containsKey(player)) defaultColors.put(player, Log4m.GetDB().getDefaultColor(player));
        return defaultColors.get(player);
    }

    public static ChatColor GetHighlightColor(Player player)
    {
        if(!highlightColor.containsKey(player)) highlightColor.put(player, Log4m.GetDB().getHighlightColor(player));
        return highlightColor.get(player);
    }

    public static boolean SetDefaultColor(Player player, String color)
    {
        color = ChatColorUtility.validateColorCode(color);
        if(player == null || color == null) return false;
        Log4m.GetDB().upsertDefaultColor(player, color);
        defaultColors.put(player, ChatColor.of(color));
        return true;
    }

    public static boolean SetHighlightColor(Player player, String color)
    {
        color = ChatColorUtility.validateColorCode(color);
        if(player == null || color == null) return false;
        Log4m.GetDB().upsertHighlightColor(player, color);
        highlightColor.put(player, ChatColor.of(color));
        return true;
    }
}
