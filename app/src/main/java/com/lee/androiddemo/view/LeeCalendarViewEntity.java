package com.lee.androiddemo.view;

import java.util.Calendar;

/**
 * Created by android on 2017/6/12.
 */

public class LeeCalendarViewEntity {

    private Calendar calendar;

    private int selectedDate = -1;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(int selectedDate) {
        this.selectedDate = selectedDate;
    }
}
