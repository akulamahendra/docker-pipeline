package com.cardie.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    void testInitialState() {
        assertFalse(gameState.isGameStarted());
        assertFalse(gameState.isGameFinished());
        assertEquals(0, gameState.getMatchesFound());
        assertNull(gameState.getFirstCard());
        assertNull(gameState.getSecondCard());
    }

    @Test
    void testInitializeCards() {
        List<String> images = Arrays.asList("🐶", "🐱", "🐭");
        gameState.initializeCards(images);
        
        assertEquals(6, gameState.getCards().size()); // 3 pairs = 6 cards
        assertEquals(3, gameState.getTotalPairs());
    }

    @Test
    void testCardSelection() {
        List<String> images = Arrays.asList("🐶", "🐱");
        gameState.initializeCards(images);
        
        Card card1 = gameState.getCards().get(0);
        Card card2 = gameState.getCards().get(1);
        
        gameState.setFirstCard(card1);
        assertEquals(card1, gameState.getFirstCard());
        
        gameState.setSecondCard(card2);
        assertEquals(card2, gameState.getSecondCard());
    }

    @Test
    void testClearSelectedCards() {
        List<String> images = Arrays.asList("🐶");
        gameState.initializeCards(images);
        
        Card card1 = gameState.getCards().get(0);
        Card card2 = gameState.getCards().get(1);
        
        gameState.setFirstCard(card1);
        gameState.setSecondCard(card2);
        
        gameState.clearSelectedCards();
        
        assertNull(gameState.getFirstCard());
        assertNull(gameState.getSecondCard());
    }

    @Test
    void testIncrementMatches() {
        assertEquals(0, gameState.getMatchesFound());
        
        gameState.incrementMatches();
        assertEquals(1, gameState.getMatchesFound());
        
        gameState.incrementMatches();
        assertEquals(2, gameState.getMatchesFound());
    }

    @Test
    void testGameTimer() throws InterruptedException {
        gameState.setGameStarted(true);
        gameState.setStartTime(System.currentTimeMillis());
        
        Thread.sleep(1100); // Wait a bit more than 1 second
        
        long elapsed = gameState.getElapsedTime();
        assertTrue(elapsed >= 1, "Elapsed time should be at least 1 second");
    }

    @Test
    void testGameNotStartedTimer() {
        assertEquals(0, gameState.getElapsedTime());
    }

    @Test
    void testGameStateFlags() {
        assertFalse(gameState.isGameStarted());
        gameState.setGameStarted(true);
        assertTrue(gameState.isGameStarted());
        
        assertFalse(gameState.isGameFinished());
        gameState.setGameFinished(true);
        assertTrue(gameState.isGameFinished());
    }
}
