package com.lee.androiddemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.lee.androiddemo.R;


public class MyFragment extends BaseFragment {

    public MyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MyFragment","onCreate");
    }

    @Override
    protected int getContextView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
