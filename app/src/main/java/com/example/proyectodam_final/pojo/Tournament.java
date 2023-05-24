package com.example.proyectodam_final.pojo;
public class Tournament {
    private String title;
    private String date;
    private String hour;
    private String location;
    private double priceEntry;
    private double reward;
    private String platform;
    private int totalPlayers;
    private int inscribedPlayers;
    private String background;

    public Tournament(String title, String date, String hour, String location, double priceEntry, double reward,
                      String platform, int totalPlayers, int inscribedPlayers, String background) {
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.priceEntry = priceEntry;
        this.reward = reward;
        this.platform = platform;
        this.totalPlayers = totalPlayers;
        this.inscribedPlayers = inscribedPlayers;
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getLocation() {
        return location;
    }

    public double getPriceEntry() {
        return priceEntry;
    }

    public double getReward() {
        return reward;
    }

    public String getPlatform() {
        return platform;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public int getInscribedPlayers() {
        return inscribedPlayers;
    }

    public String getBackground() {
        return background;
    }
}
