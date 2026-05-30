package com.cardie.util;

import com.cardie.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LeaderboardManagerTest {
    private LeaderboardManager manager;
    private static final String TEST_FILE = "leaderboard.json";

    @BeforeEach
    void setUp() {
        manager = new LeaderboardManager();
        // Clean up any existing test file
        deleteTestFile();
    }

    @AfterEach
    void tearDown() {
        deleteTestFile();
    }

    private void deleteTestFile() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testLoadEmptyLeaderboard() {
        List<Player> players = manager.loadLeaderboard();
        assertNotNull(players);
        assertTrue(players.isEmpty());
    }

    @Test
    void testAddPlayer() {
        Player player = new Player("Alice", 100);
        manager.addPlayer(player);
        
        List<Player> players = manager.loadLeaderboard();
        assertEquals(1, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals(100, players.get(0).getTimeInSeconds());
    }

    @Test
    void testAddMultiplePlayers() {
        manager.addPlayer(new Player("Alice", 150));
        manager.addPlayer(new Player("Bob", 100));
        manager.addPlayer(new Player("Charlie", 125));
        
        List<Player> players = manager.loadLeaderboard();
        assertEquals(3, players.size());
        
        // Should be sorted by time
        assertEquals("Bob", players.get(0).getName());
        assertEquals("Charlie", players.get(1).getName());
        assertEquals("Alice", players.get(2).getName());
    }

    @Test
    void testMaxEntries() {
        // Add more than 10 players
        for (int i = 0; i < 15; i++) {
            manager.addPlayer(new Player("Player" + i, 100 + i));
        }
        
        List<Player> players = manager.loadLeaderboard();
        assertEquals(10, players.size()); // Should keep only top 10
    }

    @Test
    void testGetPlayerRank() {
        manager.addPlayer(new Player("Alice", 150));
        manager.addPlayer(new Player("Bob", 100));
        manager.addPlayer(new Player("Charlie", 125));
        
        Player newPlayer = new Player("Dave", 110);
        int rank = manager.getPlayerRank(newPlayer);
        
        assertEquals(2, rank); // Should be ranked 2nd
    }

    @Test
    void testGetPlayerRankFirst() {
        manager.addPlayer(new Player("Alice", 150));
        manager.addPlayer(new Player("Bob", 100));
        
        Player newPlayer = new Player("Charlie", 50);
        int rank = manager.getPlayerRank(newPlayer);
        
        assertEquals(1, rank); // Should be ranked 1st
    }

    @Test
    void testSaveAndLoadLeaderboard() {
        Player player1 = new Player("Alice", 100);
        Player player2 = new Player("Bob", 150);
        
        manager.addPlayer(player1);
        manager.addPlayer(player2);
        
        // Create new manager to test persistence
        LeaderboardManager newManager = new LeaderboardManager();
        List<Player> players = newManager.loadLeaderboard();
        
        assertEquals(2, players.size());
        assertEquals("Alice", players.get(0).getName());
        assertEquals("Bob", players.get(1).getName());
    }
}
