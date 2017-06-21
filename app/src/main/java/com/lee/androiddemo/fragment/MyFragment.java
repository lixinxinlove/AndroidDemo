package com.lee.androiddemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;
import com.lee.androiddemo.activity.AnimatorActivity;
import com.lee.androiddemo.activity.CalendarActivity;
import com.lee.androiddemo.activity.MPAndroidChartActivity;
import com.lee.androiddemo.activity.ServiceActivity;


public class MyFragment extends BaseFragment implements View.OnClickListener {


    Button btnCalender;
    Button btnAnim;
    Button btnChart;
    Button btnService;

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
        btnAnim = (Button) rootView.findViewById(R.id.btn_anim);
        btnChart = (Button) rootView.findViewById(R.id.btn_chart);
        btnService = (Button) rootView.findViewById(R.id.btn_service);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnCalender.setOnClickListener(this);
        btnAnim.setOnClickListener(this);
        btnChart.setOnClickListener(this);
        btnService.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_anim:
                getActivity().startActivity(new Intent(getActivity(), AnimatorActivity.class));
                break;
            case R.id.btn_calender:
                getActivity().startActivity(new Intent(getActivity(), CalendarActivity.class));
                break;
            case R.id.btn_chart:
                getActivity().startActivity(new Intent(getActivity(), MPAndroidChartActivity.class));
                break;
            case R.id.btn_service:
                getActivity().startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
            default:
                break;
        }
    }


}
