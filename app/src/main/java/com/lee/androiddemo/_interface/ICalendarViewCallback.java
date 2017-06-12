package com.lee.androiddemo._interface;

import android.view.View;

import com.lee.androiddemo.view.DayEntity;


/**
 * Created by android on 2017/5/2.
 */
public interface ICalendarViewCallback {

    void onToday(int date);

    void onSelectedDate(View view, DayEntity dayView);

}
