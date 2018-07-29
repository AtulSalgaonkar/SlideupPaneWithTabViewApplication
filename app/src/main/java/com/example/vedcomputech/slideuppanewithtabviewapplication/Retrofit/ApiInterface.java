package com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit;

import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Pojo.DatePojoOne;
import com.example.vedcomputech.slideuppanewithtabviewapplication.Retrofit.Pojo.DatePojoTwo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 12-04-2018.
 */
public interface ApiInterface {

    // Fragment 1
    @GET("posts/{id}/comments")
    Observable<List<DatePojoOne>> getRandomDataOne(@Path("id") int id);

    // Fragment 2
    @GET("posts")
    Observable<List<DatePojoTwo>> getRandomDataTwo();

}
