package com.example.proyectodam_final.pojo;

public class Booking {
    private String seat;
    private String user;
    private String date;
    private String status;

    public Booking(String seat, String user, String date, String status) {
        this.seat = seat;
        this.user = user;
        this.date = date;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

