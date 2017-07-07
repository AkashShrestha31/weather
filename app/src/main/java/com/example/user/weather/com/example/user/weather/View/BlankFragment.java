package com.example.user.weather.com.example.user.weather.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class BlankFragment extends Fragment {

    API api;
    Observable<position> observable;
    TextView place, temp,max,min;
    CompositeSubscription mCompositeSubscription;
    @Override
    public void onDestroyView() {
        mCompositeSubscription.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        mCompositeSubscription = new CompositeSubscription();
        place = (TextView) view.findViewById(R.id.nameof_place);
        temp = (TextView) view.findViewById(R.id.temperature);
        max=(TextView)view.findViewById(R.id.maxtemp);
        min=(TextView)view.findViewById(R.id.mintemp);
        api = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(API.class);

        observable = currentlocation.getcurrentlocation(api,getActivity());
    /* observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<position>() {
                    @Override
                    public void call(position positions) {
                        getweatherdata(positions.getLatitude(),positions.getLongitude());
                    }

                });*/
        mCompositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<position>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
Log.d("ak","form ocompos"+e);
                    }

                    @Override
                    public void onNext(position positions) {
                        getweatherdata(positions.getLatitude(), positions.getLongitude());
                    }
                }));
        //  Log.d("ak",""+mweather.get(0).getTemp());
        return view;
    }
    public void getweatherdata(double latitude, double longitude) {
        Log.d("ak", "latitude" + latitude);
        Log.d("ak", "longitude" + longitude);
        api.getcurrentweatherdata(latitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<currentweathemodel>() {
                    @Override
                    public void call(currentweathemodel weathemodel) {
                        Log.d("ak call", "" + weathemodel.getName());
                        for (currentweathemodel.WeatherBean model : weathemodel.getWeather()) {
                            String str = Integer.toString(weathemodel.getMain().getTemp())+" °C";
                            place.setText(weathemodel.getName());
                            temp.setText(str);
                            max.setText(Integer.toString(weathemodel.getMain().getTemp_max())+" °C ");
                            min.setText(" "+Integer.toString(weathemodel.getMain().getTemp_min())+" °C ");
                            Log.d("ak ygfcvx error", "" + model.getDescription());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("ak", "" + throwable);
                    }
                });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


}
