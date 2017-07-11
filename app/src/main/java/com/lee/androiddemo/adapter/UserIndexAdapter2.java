package com.lee.androiddemo.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lee.androiddemo.R;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.List;

/**
 * Created by android on 2017/6/22.
 */

public class UserIndexAdapter2 extends BaseQuickAdapter<String, BaseViewHolder> {

    public UserIndexAdapter2(@Nullable List<String> data) {
        super(R.layout.item_view, data);
    }

    String mCurrentChar = "";

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        String[] ar = PinyinHelper.toHanyuPinyinStringArray(item.charAt(0));

        helper.setText(R.id.tv_text, item);
        helper.getView(R.id.tv_top).setVisibility(View.GONE);


        if (mCurrentChar.equals(ar[0].substring(0, 1))) {
            helper.getView(R.id.tv_top).setVisibility(View.GONE);
        } else {
            helper.setText(R.id.tv_top, ar[0].substring(0, 1).toUpperCase());
            helper.getView(R.id.tv_top).setVisibility(View.VISIBLE);
            mCurrentChar = ar[0].substring(0, 1);
        }

    }
}
