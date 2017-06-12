package com.lee.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lee.androiddemo.R;
import com.lee.androiddemo.view.LeeCalendarView;
import com.lee.androiddemo.view.LeeCalendarViewEntity;

import java.util.List;

/**
 * Created by android on 2017/6/12.
 */

public class CalendarAdapter extends BaseAdapter {

    private Context context;

    List<LeeCalendarViewEntity> mData;

    public CalendarAdapter(Context context, List<LeeCalendarViewEntity> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public LeeCalendarViewEntity getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeeCalendarViewEntity entity = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_calendar_view, null);
            holder.leeCalendarView = (LeeCalendarView) convertView.findViewById(R.id.lee_cv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.leeCalendarView.setCalendar(entity.getCalendar());
        holder.leeCalendarView.setSelectedDate(entity.getSelectedDate());
        holder.leeCalendarView.refresh();
        return convertView;
    }

    static class ViewHolder {
        LeeCalendarView leeCalendarView;
    }

}
