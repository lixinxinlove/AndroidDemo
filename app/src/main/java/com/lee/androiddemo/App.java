package com.lee.androiddemo;

import android.app.Application;

import com.lee.androiddemo.http.Api;

/**
 * Created by lixinxin on 2017/3/21.
 */

public class App extends Application {
    public static Api api;

    @Override
    public void onCreate() {
        super.onCreate();
        api = new Api();
    }
}
