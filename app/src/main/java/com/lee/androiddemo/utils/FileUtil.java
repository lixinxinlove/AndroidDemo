package com.lee.androiddemo.utils;

import android.os.Environment;

/**
 * Created by android on 2017/7/18.
 */
public class FileUtil {

    public static String getBasePath() {

        String path = Environment.getExternalStorageDirectory().getPath();
        return path;
    }
}
