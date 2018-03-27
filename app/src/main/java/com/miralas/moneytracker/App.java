package com.miralas.moneytracker;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiburon on 27/03/2018.
 */

public class App extends Application {

    private Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        Gson gson = new GsonBuilder()
                .setDateFormat("dd.mm.yyyy hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://moneytrackertest.getsandbox.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    public Api getApi() {
        return api;
    }
}
