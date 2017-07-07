package com.example.user.weather.com.example.user.weather.presentation;

import com.example.user.weather.com.example.user.weather.model.currentweathemodel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Single;


public interface API {
    @GET("weather?units=metric&cnt=15&appid=a1fe97e352c20bb4cba98a2db846c9c8")
    Observable<currentweathemodel> getcurrentweatherdata(@Query("lat")double latitude, @Query("lon")double longitude);

}
