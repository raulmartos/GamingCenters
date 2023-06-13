package com.example.proyectodam_final.pojo;

public class Booking {
    private String user;
    private String date;
    private boolean status;
    private long createdAt;


    public Booking(String user, String date, boolean status, long createdAt) {
        this.user = user;
        this.date = date;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Booking(String user, String date, long createdAt) {
        this.user = user;
        this.date = date;
        this.createdAt = createdAt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}

