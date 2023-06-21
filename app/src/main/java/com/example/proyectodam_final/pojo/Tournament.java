package com.example.proyectodam_final.pojo;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;

public class Tournament implements Serializable {
    private String title, date, hour, location, platform;
    private double priceEntry, reward;
    private int totalPlayers;

    public Tournament() {
    }

    public Tournament(String title, String date, String hour, String location, double priceEntry, double reward,
                      String platform, int totalPlayers) {
        this.title = title;
        this.date = date;
        this.hour = hour;
        this.location = location;
        this.priceEntry = priceEntry;
        this.reward = reward;
        this.platform = platform;
        this.totalPlayers = totalPlayers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPriceEntry() {
        return priceEntry;
    }

    public void setPriceEntry(double priceEntry) {
        this.priceEntry = priceEntry;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }
}