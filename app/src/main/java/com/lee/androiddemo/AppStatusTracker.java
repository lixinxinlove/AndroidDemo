package com.lee.androiddemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by android on 2017/7/7.
 */

public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {

    private final static String TAG = "AppStatusTracker";
    private int activeCount=0;
    private Application application;
    private static AppStatusTracker tracker;
    private AppStatusTracker(Application application) {
        this.application = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        tracker = new AppStatusTracker(application);
    }


    public static AppStatusTracker getInstance() {
        return tracker;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.e(TAG, "onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.e(TAG, "onActivityStarted");
        activeCount++;
        Log.e(TAG, "ActiveCount=="+activeCount);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.e(TAG, "onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.e(TAG, "onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.e(TAG, "onActivityStopped");
        activeCount--;
        Log.e(TAG, "ActiveCount=="+activeCount);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.e(TAG, "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.e(TAG, "onActivityDestroyed");

    }
}
