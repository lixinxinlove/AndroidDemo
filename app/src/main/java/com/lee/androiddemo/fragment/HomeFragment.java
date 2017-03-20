package com.lee.androiddemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.androiddemo.R;


public class HomeFragment extends BaseFragment {


    private TextView tv;
    private String title;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeFragment", "onCreate");
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findView() {
        tv = (TextView) rootView.findViewById(R.id.textView);
    }

    @Override
    protected void initData() {
        tv.setText(title);
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
