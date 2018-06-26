package com.example.access_pc;

import android.support.annotation.NonNull;

import com.example.access_pc.interfaces.MenuApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Access_pc on 16/05/2018.
 */

public class Retro {

    public static MenuApi getRandomUserService(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://e-food-system.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(MenuApi.class);
    }

    public static MenuApi getUser(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://e-food-system.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(MenuApi.class);
    }

}
