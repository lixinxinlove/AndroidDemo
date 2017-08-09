package com.lee.androiddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by android on 2017/7/28.
 */

public class NoHistoryActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //全屏
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "NoHistoryActivity", Toast.LENGTH_LONG).show();
        Log.e("NoHistoryActivity", "onCreate");

       // startActivity(new Intent(this, GestureLockActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("NoHistoryActivity", "onDestroy");


    }

}
