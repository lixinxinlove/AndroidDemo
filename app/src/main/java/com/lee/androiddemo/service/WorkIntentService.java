package com.lee.androiddemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class WorkIntentService extends IntentService {

    public WorkIntentService() {
        super("WorkIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.e("WorkIntentService", "onHandleIntent");

        if (intent != null) {
            Log.e("WorkIntentService", "woke...");
        }
    }
}
