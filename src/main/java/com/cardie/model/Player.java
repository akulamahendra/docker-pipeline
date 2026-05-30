package com.cardie.model;

public class Player implements Comparable<Player> {
    private String name;
    private long timeInSeconds;

    public Player(String name, long timeInSeconds) {
        this.name = name;
        this.timeInSeconds = timeInSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public int compareTo(Player other) {
        return Long.compare(this.timeInSeconds, other.timeInSeconds);
    }

    public String getFormattedTime() {
        long minutes = timeInSeconds / 60;
        long seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
