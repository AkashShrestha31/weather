package com.example.user.weather.com.example.user.weather.View;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Implement an Rx-style location service by wrapping the Android LocationManager and providing
 * the location result as an Observable.
 */
public class LocationService {
    private final LocationManager mLocationManager;
    Context context;

    public LocationService(LocationManager locationManager,Context context) {
        mLocationManager = locationManager;
        this.context=context;
    }

    public Observable<Location> getLocation() {
        return Observable.create(new Observable.OnSubscribe<Location>() {
            @Override
            public void call(final Subscriber<? super Location> subscriber) {

                final LocationListener locationListener = new LocationListener() {
                    public void onLocationChanged(final Location location) {
                        subscriber.onNext(location);
                        subscriber.onCompleted();
                        Looper.getMainLooper().quit();
                    }

                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }
                };

                final Criteria locationCriteria = new Criteria();
                locationCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
                locationCriteria.setPowerRequirement(Criteria.POWER_LOW);
                final String locationProvider = mLocationManager
                        .getBestProvider(locationCriteria, true);
                if (Looper.myLooper() == null)
                Looper.prepare();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestSingleUpdate(locationProvider,
                        locationListener, Looper.myLooper());

                Looper.loop();
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.d("ak","error"+throwable);
            }
        });
    }
}