package com.cardie.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testCardCreation() {
        Card card = new Card("🐶", 1);
        assertEquals("🐶", card.getEmoji());
        assertEquals(1, card.getId());
        assertFalse(card.isFlipped());
        assertFalse(card.isMatched());
    }

    @Test
    void testCardFlip() {
        Card card = new Card("🐱", 2);
        assertFalse(card.isFlipped());
        
        card.setFlipped(true);
        assertTrue(card.isFlipped());
        
        card.setFlipped(false);
        assertFalse(card.isFlipped());
    }

    @Test
    void testCardMatch() {
        Card card = new Card("🐭", 3);
        assertFalse(card.isMatched());
        
        card.setMatched(true);
        assertTrue(card.isMatched());
    }

    @Test
    void testCardEquality() {
        Card card1 = new Card("🐶", 1);
        Card card2 = new Card("🐶", 1);
        Card card3 = new Card("🐱", 2);
        
        assertEquals(card1, card2);
        assertNotEquals(card1, card3);
    }

    @Test
    void testCardHashCode() {
        Card card1 = new Card("🐶", 1);
        Card card2 = new Card("🐶", 1);
        
        assertEquals(card1.hashCode(), card2.hashCode());
    }
}
