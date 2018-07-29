package com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 11-04-2018.
 */
public class Api {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit mRetrofit;

    private Api() {

    }

    public static Retrofit getRetrofitOfFileUpload() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return mRetrofit;
    }

}
