package com.lee.androiddemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;
import com.lee.androiddemo.activity.CalendarActivity;


public class MyFragment extends BaseFragment implements View.OnClickListener {


    Button btnCalender;

    public MyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MyFragment", "onCreate");

    }

    @Override
    protected int getContextView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void findView() {
        btnCalender = (Button) rootView.findViewById(R.id.btn_calender);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnCalender.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), CalendarActivity.class));
    }
}
