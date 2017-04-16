package com.lee.androiddemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lee.androiddemo.R;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {


    @BindView(R.id.root_bg)
    View rootBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        rootBg.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, FragmentTabHostActivity.class));
                finish();
            }
        }, 3000);

    }
}
