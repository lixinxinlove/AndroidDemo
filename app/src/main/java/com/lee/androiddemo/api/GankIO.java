package com.lee.androiddemo.api;

import com.lee.androiddemo.api.data.GankAndroid;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by android on 2017/7/11.
 */

public interface GankIO {

    @GET("Android/{month}/{day}")
    Observable<GankAndroid> getAndroid(@Path("month") int month, @Path("day") int day);

}
