package com.cardie.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ThemeManagerTest {
    private ThemeManager themeManager;

    @BeforeEach
    void setUp() {
        themeManager = new ThemeManager();
    }

    @Test
    void testDefaultTheme() {
        assertTrue(themeManager.isDarkMode());
    }

    @Test
    void testToggleTheme() {
        assertTrue(themeManager.isDarkMode());
        
        themeManager.toggleTheme();
        assertFalse(themeManager.isDarkMode());
        
        themeManager.toggleTheme();
        assertTrue(themeManager.isDarkMode());
    }

    @Test
    void testDarkModeColors() {
        assertTrue(themeManager.isDarkMode());
        
        assertEquals("#1a1a2e", themeManager.getBackgroundColor());
        assertEquals("#16213e", themeManager.getCardBackgroundColor());
        assertEquals("#eaeaea", themeManager.getTextColor());
        assertEquals("#0f3460", themeManager.getAccentColor());
        assertEquals("#e94560", themeManager.getButtonColor());
        assertEquals("#ff6b81", themeManager.getButtonHoverColor());
        assertEquals("#0f3460", themeManager.getCardBorderColor());
    }

    @Test
    void testLightModeColors() {
        themeManager.toggleTheme(); // Switch to light mode
        assertFalse(themeManager.isDarkMode());
        
        assertEquals("#f0f0f0", themeManager.getBackgroundColor());
        assertEquals("#ffffff", themeManager.getCardBackgroundColor());
        assertEquals("#333333", themeManager.getTextColor());
        assertEquals("#4a90e2", themeManager.getAccentColor());
        assertEquals("#5cb85c", themeManager.getButtonColor());
        assertEquals("#4cae4c", themeManager.getButtonHoverColor());
        assertEquals("#ddd", themeManager.getCardBorderColor());
    }

    @Test
    void testColorConsistency() {
        String darkBg = themeManager.getBackgroundColor();
        assertEquals(darkBg, themeManager.getBackgroundColor());
        
        themeManager.toggleTheme();
        String lightBg = themeManager.getBackgroundColor();
        assertNotEquals(darkBg, lightBg);
        
        themeManager.toggleTheme();
        assertEquals(darkBg, themeManager.getBackgroundColor());
    }
}
