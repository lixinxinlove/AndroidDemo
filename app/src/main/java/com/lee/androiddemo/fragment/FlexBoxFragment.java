package com.lee.androiddemo.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lee.androiddemo.R;
import com.lee.androiddemo.adapter.UserAdapter;
import com.lee.androiddemo.bean.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlexBoxFragment extends BaseFragment {


    private RecyclerView recyclerView;

    private UserAdapter adapter;

    private List<UserEntity> mData;

    private View headerView;


    public FlexBoxFragment() {
    }


    @Override
    protected int getContextView() {
        return R.layout.fragment_flexbox;
    }

    @Override
    protected void findView() {
        headerView = View.inflate(getContext(), R.layout.header_view, null);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {

        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mData.add(new UserEntity("名字" + i, i));
        }
        adapter = new UserAdapter(mData);
        adapter.addHeaderView(headerView);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

}
