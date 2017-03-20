package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lee.androiddemo.exception.AppManager;

import butterknife.ButterKnife;

/**
 * Created by lixinxin on 2017/3/20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(getContextView());
        ButterKnife.bind(this);
        initListener();
        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }


    protected abstract int getContextView();

    protected abstract void initListener();

    protected abstract void initData();

}
