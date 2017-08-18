package com.lee.androiddemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;
import com.lee.androiddemo.activity.AnimatorActivity;
import com.lee.androiddemo.activity.CalendarActivity;
import com.lee.androiddemo.activity.GestureLockActivity;
import com.lee.androiddemo.activity.MPAndroidChartActivity;
import com.lee.androiddemo.activity.Rxjava2Activity;
import com.lee.androiddemo.activity.ScrollingActivity;
import com.lee.androiddemo.activity.ServiceActivity;
import com.lee.androiddemo.activity.UpdateActivity;
import com.lee.androiddemo.view.ActSwitchAnimTool;


public class MyFragment extends BaseFragment implements View.OnClickListener {


    Button btnCalender;
    Button btnAnim;
    Button btnChart;
    Button btnService;
    Button btnRx;
    Button btnDown;
    Button btnPassword;
    Button btnWeb;
    Button btnNoHistory;

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
        btnRx = (Button) rootView.findViewById(R.id.btn_rxjava);
        btnDown = (Button) rootView.findViewById(R.id.btn_down);
        btnPassword = (Button) rootView.findViewById(R.id.btn_password);
        btnWeb = (Button) rootView.findViewById(R.id.btn_web);
        btnNoHistory = (Button) rootView.findViewById(R.id.btn_no_history);
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
        btnRx.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnPassword.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnNoHistory.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_anim:
                getActivity().startActivity(new Intent(getActivity(), AnimatorActivity.class));
                break;
            case R.id.btn_calender:

                Intent intent1 = new Intent(getActivity(), CalendarActivity.class);
                ActSwitchAnimTool mFirstDemoActSwitchAnimTool = new ActSwitchAnimTool(getActivity()).setAnimType(ActSwitchAnimTool.MODE_UNINIT)

                        .target(btnCalender)
                        .setShrinkBack(false)
                        .setmColorStart(Color.parseColor("#88FF5777"))
                        .setmColorEnd(Color.parseColor("#88FF5777"))
                        .startActivity(intent1, false);

                mFirstDemoActSwitchAnimTool.setAnimType(ActSwitchAnimTool.MODE_SPREAD)
                        .build();

               // getActivity().startActivity(new Intent(getActivity(), CalendarActivity.class));
                break;
            case R.id.btn_chart:
                getActivity().startActivity(new Intent(getActivity(), MPAndroidChartActivity.class));
                break;
            case R.id.btn_service:
                getActivity().startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
            case R.id.btn_rxjava:
                getActivity().startActivity(new Intent(getActivity(), Rxjava2Activity.class));
                break;
            case R.id.btn_down:
                getActivity().startActivity(new Intent(getActivity(), UpdateActivity.class));
                break;
            case R.id.btn_password:
                getActivity().startActivity(new Intent(getActivity(), GestureLockActivity.class));
                break;
            case R.id.btn_no_history:
                getActivity().startActivity(new Intent(getActivity(), ScrollingActivity.class));
                break;
            case R.id.btn_web:

                Intent intent = new Intent();
                intent.setAction("com.lee.androiddemo.work");
                intent.setPackage("com.lee.androiddemo");
                getActivity().sendBroadcast(intent);

                //getActivity().startActivity(new Intent(getActivity(), PhotoActivity.class));
                break;
            default:
                break;
        }
    }


}
