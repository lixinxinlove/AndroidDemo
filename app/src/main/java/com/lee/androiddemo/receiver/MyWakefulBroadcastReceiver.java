package com.lee.androiddemo.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.lee.androiddemo.service.WorkIntentService;

/**
 * Created by android on 2017/7/28.
 */
public class MyWakefulBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, WorkIntentService.class);
        // 启动 service 并保持设备唤醒状态直到调用 completeWakefulIntent()
        startWakefulService(context, service);
        Log.e("MyWakeful","启动服务");
    }
}
