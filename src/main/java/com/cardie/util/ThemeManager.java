package com.cardie.util;

public class ThemeManager {
    private boolean isDarkMode;

    public ThemeManager() {
        this.isDarkMode = true; // Default to dark mode
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void toggleTheme() {
        isDarkMode = !isDarkMode;
    }

    public String getBackgroundColor() {
        return isDarkMode ? "#1a1a2e" : "#f0f0f0";
    }

    public String getCardBackgroundColor() {
        return isDarkMode ? "#16213e" : "#ffffff";
    }

    public String getTextColor() {
        return isDarkMode ? "#eaeaea" : "#333333";
    }

    public String getAccentColor() {
        return isDarkMode ? "#0f3460" : "#4a90e2";
    }

    public String getButtonColor() {
        return isDarkMode ? "#e94560" : "#5cb85c";
    }

    public String getButtonHoverColor() {
        return isDarkMode ? "#ff6b81" : "#4cae4c";
    }

    public String getCardBorderColor() {
        return isDarkMode ? "#0f3460" : "#ddd";
    }
}
