package com.lee.androiddemo.exception;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class AppManager {

    private static List<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new ArrayList<>();
        }
        activityStack.add(activity);
    }

    /**
     * 添加Activity到堆栈
     */
    public void removeActivity(Activity activity) {

        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
