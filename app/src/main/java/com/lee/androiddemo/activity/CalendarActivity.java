package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.lee.androiddemo.R;
import com.lee.androiddemo.adapter.CalendarAdapter;
import com.lee.androiddemo.view.LeeCalendarViewEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

public class CalendarActivity extends BaseActivity {

    @BindView(R.id.list_view)
    ListView listView;

    private CalendarAdapter adapter;
    private List<LeeCalendarViewEntity> mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        adapter = new CalendarAdapter(this, mData);
        listView.setAdapter(adapter);

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
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, i);
            LeeCalendarViewEntity entity = new LeeCalendarViewEntity();
            entity.setCalendar(calendar);
            entity.setSelectedDate(-1);
            mData.add(entity);
        }
    }
}
