package com.example.user.weather.com.example.user.weather.presentation;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.example.user.weather.com.example.user.weather.View.LocationService;
import com.example.user.weather.com.example.user.weather.model.position;

import rx.Observable;
import rx.functions.Func1;

public class currentlocation {
    static API api;


    public static Observable<position> getcurrentlocation(API a, final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationService locationService = new LocationService(locationManager, context);
        /*final Observable<String> fetchDataObservable = locationService.getLocation()
                .map(new Func1<Location, String>() {
                    @Override
                    public String call(Location location) {
                     new position(location.getLatitude(),location.getLongitude());
                        return null;
                    }
                });*/
       /* fetchDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("ak observable", "" + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("ak", "" + throwable);
                    }
                });*/
      //  Log.d("ak",""+mweather.size());
        return locationService.getLocation()
                .map(new Func1<Location, position>() {
                    @Override
                    public position call(Location location) {
                        new position(location.getLatitude(),location.getLongitude());
                        return new position(location.getLatitude(),location.getLongitude());
                    }
                });
    }

}
