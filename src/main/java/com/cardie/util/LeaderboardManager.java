package com.cardie.util;

import com.cardie.model.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardManager {
    private static final String LEADERBOARD_FILE = "leaderboard.json";
    private static final int MAX_ENTRIES = 10;
    private final Gson gson;

    public LeaderboardManager() {
        this.gson = new Gson();
    }

    public List<Player> loadLeaderboard() {
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Player>>(){}.getType();
            List<Player> players = gson.fromJson(reader, listType);
            return players != null ? players : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveLeaderboard(List<Player> players) {
        try (Writer writer = new FileWriter(LEADERBOARD_FILE)) {
            gson.toJson(players, writer);
        } catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
        }
    }

    public void addPlayer(Player player) {
        List<Player> players = loadLeaderboard();
        players.add(player);
        Collections.sort(players);
        
        // Keep only top entries
        if (players.size() > MAX_ENTRIES) {
            players = players.subList(0, MAX_ENTRIES);
        }
        
        saveLeaderboard(players);
    }

    public int getPlayerRank(Player player) {
        List<Player> players = loadLeaderboard();
        players.add(player);
        Collections.sort(players);
        
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName()) && 
                players.get(i).getTimeInSeconds() == player.getTimeInSeconds()) {
                return i + 1;
            }
        }
        return -1;
    }
}
