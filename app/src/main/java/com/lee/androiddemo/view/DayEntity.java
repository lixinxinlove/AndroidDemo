package com.lee.androiddemo.view;

import android.support.annotation.NonNull;

/**
 * Created by android on 2017/5/24.
 */

public class DayEntity {

    private int index;

    private int year;
    private int month;
    private int day;

    private int today;
    private boolean selected;
    private boolean drawBg;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getToday() {
        return today;
    }

    public void setToday(int today) {
        this.today = today;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDrawBg() {
        return drawBg;
    }

    public void setDrawBg(boolean drawBg) {
        this.drawBg = drawBg;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DayEntity that = (DayEntity) o;

        return day == that.day && month == that.month && year == that.year;
    }

    public boolean isBefore(@NonNull DayEntity other) {
        if (other == null) {
            throw new IllegalArgumentException("other cannot be null");
        }
        if (year == other.year) {
            return ((month == other.month) ? (day < other.day) : (month < other.month));
        } else {
            return year < other.year;
        }
    }

    public boolean isAfter(@NonNull DayEntity other) {
        if (other == null) {
            throw new IllegalArgumentException("other cannot be null");
        }

        if (year == other.year) {
            return (month == other.month) ? (day > other.day) : (month > other.month);
        } else {
            return year > other.year;
        }
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }
}
