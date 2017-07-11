package com.lee.androiddemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lee.androiddemo.R;
import com.lee.androiddemo.adapter.UserIndexAdapter;
import com.lee.androiddemo.config.AppConfig;
import com.lee.androiddemo.http.RequestCallback;
import com.lee.androiddemo.http.ResponseEntity;
import com.lee.androiddemo.view.IndexView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private IndexView mIndexView;
    private RecyclerView mRecyclerView;


    private String title;


    private UserIndexAdapter adapter;

    private List<String> mData;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeFragment", "onCreate");
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    protected int getContextView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findView() {
        mIndexView = (IndexView) rootView.findViewById(R.id.index);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_user);
    }

    @Override
    protected void initData() {
        //tv.setText(title);
        // App.api.login(callback);
        mData = new ArrayList<>();
        mData = Arrays.asList(AppConfig.NAMES);


        Collections.sort(mData, Collator.getInstance(java.util.Locale.CHINA));

        adapter = new UserIndexAdapter(mData);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mIndexView.setOnIndexTypeListener(new IndexView.OnIndexTypeListener() {
            @Override
            public void onIndex(String index) {
                Toast.makeText(getActivity(), index, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private RequestCallback callback = new RequestCallback() {
        @Override
        public void RequestCallback(ResponseEntity res) {
            if (res.code.equals("0")) {
            }
        }
    };

}
