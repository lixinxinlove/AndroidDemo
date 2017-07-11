package com.lee.androiddemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.androiddemo.R;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.List;

/**
 * Created by android on 2017/7/11.
 */

public class UserIndexAdapter extends RecyclerView.Adapter<UserIndexAdapter.ViewHolder> {

    private List<String> mData;

    private String mCurrentChar = "v";

    public UserIndexAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = View.inflate(parent.getContext(), R.layout.item_view, null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] ar = PinyinHelper.toHanyuPinyinStringArray(mData.get(position).charAt(0));

        if (mCurrentChar.equals(ar[0].substring(0, 1))) {
            holder.tvTop.setVisibility(View.GONE);
        } else {
            holder.tvTop.setText(ar[0].substring(0, 1));
            holder.tvTop.setVisibility(View.VISIBLE);
        }
        mCurrentChar = ar[0].substring(0, 1);
        holder.tvText.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTop;
        public TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTop = (TextView) itemView.findViewById(R.id.tv_top);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
