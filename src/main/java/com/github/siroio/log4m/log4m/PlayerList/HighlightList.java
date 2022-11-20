package com.github.siroio.log4m.log4m.PlayerList;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class HighlightList {

    private final static HashSet<Player> players = new HashSet<>();

    private HighlightList() {}

    public static void Add(Player player) {
        if(players.contains(player)) return;
        players.add(player);
    }

    public static void Remove(Player player) {
        if(!players.contains(player)) return;
        players.remove(player);
    }

    public static void Clear() {
        players.clear();
    }

    public static boolean isHighlight(Player player) {
        return players.contains(player);
    }

    public static List<String> GetPlayersToString() {
        List<String> result = new ArrayList<>();
        for(Player p : players) result.add(p.getName());
        return result;
    }

    public static List<Player> GetPlayer() {
        return new ArrayList<>(players);
    }
}
