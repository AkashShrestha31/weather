package com.example.user.weather.com.example.user.weather.model;

public class position {

 double latitude;
double longitude;

    public position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public  double getLongitude() {
        return longitude;
    }
}
