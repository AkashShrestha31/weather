package com.example.user.weather.com.example.user.weather.model;

public class weather {
    String place;
    String temp;
    String maxtemp;
    String mintemp;

    public weather(String place, String temp,String maxtemp,String mintemp) {
        this.place = place;
        this.temp = temp;
        this.maxtemp=maxtemp;
        this.mintemp=mintemp;
    }

    public String getPlace() {
        return place;
    }

    public String getTemp() {
        return temp;
    }
}
