package com.cardie.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerCreation() {
        Player player = new Player("Alice", 125);
        assertEquals("Alice", player.getName());
        assertEquals(125, player.getTimeInSeconds());
    }

    @Test
    void testPlayerComparison() {
        Player player1 = new Player("Alice", 100);
        Player player2 = new Player("Bob", 150);
        Player player3 = new Player("Charlie", 100);
        
        assertTrue(player1.compareTo(player2) < 0);
        assertTrue(player2.compareTo(player1) > 0);
        assertEquals(0, player1.compareTo(player3));
    }

    @Test
    void testFormattedTime() {
        Player player1 = new Player("Alice", 65);
        assertEquals("01:05", player1.getFormattedTime());
        
        Player player2 = new Player("Bob", 125);
        assertEquals("02:05", player2.getFormattedTime());
        
        Player player3 = new Player("Charlie", 45);
        assertEquals("00:45", player3.getFormattedTime());
    }

    @Test
    void testSetters() {
        Player player = new Player("Alice", 100);
        
        player.setName("Bob");
        assertEquals("Bob", player.getName());
        
        player.setTimeInSeconds(200);
        assertEquals(200, player.getTimeInSeconds());
    }
}
