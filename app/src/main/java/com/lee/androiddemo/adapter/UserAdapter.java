package com.lee.androiddemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lee.androiddemo.R;
import com.lee.androiddemo.bean.UserEntity;

import java.util.List;

/**
 * Created by android on 2017/6/22.
 */

public class UserAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {

    public UserAdapter(@Nullable List<UserEntity> data) {
        super(R.layout.item_user_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
        helper.setText(R.id.tv_user_name, item.getUserName()).setText(R.id.tv_user_age, item.getUserAge() + "");
    }
}
