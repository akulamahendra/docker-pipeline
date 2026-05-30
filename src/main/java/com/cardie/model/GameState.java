package com.cardie.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private List<Card> cards;
    private Card firstCard;
    private Card secondCard;
    private int matchesFound;
    private long startTime;
    private boolean gameStarted;
    private boolean gameFinished;

    public GameState() {
        this.cards = new ArrayList<>();
        this.matchesFound = 0;
        this.gameStarted = false;
        this.gameFinished = false;
    }

    public void initializeCards(List<String> emojis) {
        cards.clear();
        int id = 0;
        
        // Create pairs of cards
        for (String emoji : emojis) {
            cards.add(new Card(emoji, id));
            cards.add(new Card(emoji, id));
            id++;
        }
        
        // Shuffle the cards
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(Card firstCard) {
        this.firstCard = firstCard;
    }

    public Card getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(Card secondCard) {
        this.secondCard = secondCard;
    }

    public void clearSelectedCards() {
        this.firstCard = null;
        this.secondCard = null;
    }

    public int getMatchesFound() {
        return matchesFound;
    }

    public void incrementMatches() {
        this.matchesFound++;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public long getElapsedTime() {
        if (!gameStarted) return 0;
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public int getTotalPairs() {
        return cards.size() / 2;
    }
}
