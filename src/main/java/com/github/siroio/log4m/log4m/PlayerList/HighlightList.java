package com.github.siroio.log4m.log4m.PlayerList;

import org.bukkit.entity.Player;
import java.util.*;
import java.util.stream.Collectors;

public final class HighlightList {

    private static final Map<String, Player> players = new HashMap<>();

    private HighlightList() {}

    public static void Add(Player player) {
        if(players.containsValue(player)) return;
        players.put(player.getName(), player);
    }

    public static void Remove(Player player) {
        if(!players.containsValue(player)) return;
        players.remove(player.getName());
    }

    public static Player getPlayer(String name) {
        if(!players.containsKey(name)) return null;
        return players.get(name);
    }

    public static void Clear() {
        if(players.size() < 1) return;
        players.clear();
    }

    public static boolean isHighlight(Player player) {
        return players.containsValue(player);
    }

    public static List<String> getPlayersToString() {
        return players.values().stream().map(Player::getName).collect(Collectors.toList());
    }

    public static List<Player> GetPlayer() {
        return new ArrayList<>(players.values());
    }
}
