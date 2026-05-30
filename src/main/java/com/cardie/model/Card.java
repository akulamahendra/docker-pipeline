package com.cardie.model;

public class Card {
    private final String emoji;
    private final int id;
    private boolean isFlipped;
    private boolean isMatched;

    public Card(String emoji, int id) {
        this.emoji = emoji;
        this.id = id;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getId() {
        return id;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
