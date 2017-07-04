package com.lee.androiddemo.service;

import android.app.IntentService;
import android.content.Intent;

import com.lee.androiddemo._interface.DownloadListener;

import java.io.File;

/**
 * 下载app
 */
public class DownIntentService extends IntentService {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;


    private DownloadListener listener;

    private boolean isCanceled = false;

    private boolean isPaused = false;

    private int lastProgress;

    private File mFile;


    public DownIntentService() {
        super("DownIntentService");
    }

    public DownIntentService(DownloadListener listener) {
        super("DownIntentService");
        this.listener = listener;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

        }
    }
}
