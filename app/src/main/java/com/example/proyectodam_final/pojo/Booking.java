package com.example.proyectodam_final.pojo;

public class Booking {
    private String seat;
    private String user;
    private String date;
    private boolean status;
    private String createdAt;


    public Booking(String seat, String user, String date, boolean status, String createdAt) {
        this.seat = seat;
        this.user = user;
        this.date = date;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Booking(String seat, String user, String date) {
        this.seat = seat;
        this.user = user;
        this.date = date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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
}

