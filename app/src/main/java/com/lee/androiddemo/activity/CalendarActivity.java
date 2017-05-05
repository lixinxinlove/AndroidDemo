package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.lee.androiddemo.R;
import com.lee.androiddemo.view.CalendarView;

import java.util.Calendar;

import butterknife.BindView;

public class CalendarActivity extends BaseActivity {

    @BindView(R.id.root_view)
    LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        rootView = (LinearLayout) findViewById(R.id.root_view);
        rootView.removeAllViews();

        for (int i = 0; i < 10; i++) {
            Calendar calendar = Calendar.getInstance();
            CalendarView cv = new CalendarView(this);
            cv.setBackgroundColor(0xffffffff);
            calendar.add(Calendar.MONTH, i);
            cv.setCalendar(calendar);
            rootView.addView(cv);
        }
    }
}
