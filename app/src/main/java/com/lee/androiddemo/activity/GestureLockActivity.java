package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.lee.androiddemo.R;
import com.lee.androiddemo.view.GestureLockView;

import butterknife.BindView;

public class GestureLockActivity extends BaseActivity {


    private static final String TAG = "GestureLockActivity";

    @BindView(R.id.gesture_lock_view)
    GestureLockView lockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_gesture_lock;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        lockView.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String gestureCode) {
                Log.e(TAG, gestureCode);
            }
        });

    }
}
