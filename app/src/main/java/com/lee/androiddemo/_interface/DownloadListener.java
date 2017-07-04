package com.lee.androiddemo._interface;

/**
 * Created by android on 2017/7/4.
 */
public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
