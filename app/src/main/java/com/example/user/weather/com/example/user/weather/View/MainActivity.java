package com.example.user.weather.com.example.user.weather.View;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.user.weather.R;
import com.example.user.weather.com.example.user.weather.model.currentweathemodel;
import com.example.user.weather.com.example.user.weather.model.position;
import com.example.user.weather.com.example.user.weather.presentation.API;
import com.example.user.weather.com.example.user.weather.presentation.currentlocation;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ak","onpause is called");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new BlankFragment());
        fragmentTransaction.commit();

    }




}
