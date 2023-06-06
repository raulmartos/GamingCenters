package com.example.proyectodam_final.pojo;
public class Tournament {
    private String title, date, hour, location, platform;
    private double priceEntry, reward;
    private int totalPlayers, inscribedPlayers;

    public Tournament(String title, String date, String hour, String location, double priceEntry, double reward,
                      String platform, int totalPlayers, int inscribedPlayers) {
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.priceEntry = priceEntry;
        this.reward = reward;
        this.platform = platform;
        this.totalPlayers = totalPlayers;
        this.inscribedPlayers = inscribedPlayers;
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

}
