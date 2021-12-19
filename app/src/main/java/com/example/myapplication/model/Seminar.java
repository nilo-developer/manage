package com.example.myapplication.model;

public class Seminar {
    private String title;
    private String type;
    private String city;
    private String location;
    private String day;

    public Seminar() { }

    public Seminar(String title, String type, String city, String location, String day) {
        this.title = title;
        this.type = type;
        this.city = city;
        this.location = location;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
