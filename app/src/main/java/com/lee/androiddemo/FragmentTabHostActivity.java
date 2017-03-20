package com.lee.androiddemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.lee.androiddemo.fragment.HomeFragment;
import com.lee.androiddemo.fragment.MyFragment;
import com.lee.androiddemo.view.TabView;

public class FragmentTabHostActivity extends AppCompatActivity {


    private FragmentTabHost mTabHost;
    //定义数组存放Fragment
    private final Class fragmentArray[] = {MyFragment.class, HomeFragment.class, MyFragment.class, MyFragment.class};
    //定义数组存放图片(存放的是图片选择器包含连个状态 press和selected)
    private int mImageViewArray[] = {R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    //Tab选项卡的文字
    private String mTextViewArray[] = {"首页", "分类", "购物车", "我的"};
    private String mTabIdArray[] = {"home", "lll", "eee", "my"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);


        initFragment();

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(FragmentTabHostActivity.this, tabId, Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 初始化Fragment
     */

    private void initFragment() {
        mTabHost = (FragmentTabHost) findViewById(R.id.fragment_tab_host);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);//设置替换哪个布局
        int fragmentCount = fragmentArray.length;

        for (int i = 0; i < fragmentCount; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabIdArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            Bundle bundle = new Bundle();
            bundle.putString("title", "lee");
            mTabHost.addTab(tabSpec, fragmentArray[i], bundle);
            //  mTabHost.getTabWidget().setDividerDrawable(mImageViewArray[i]);//设置每个TabView的控件
        }
    }

    /**
     * 设置每个Item的布局
     *
     * @return
     */
    private View getTabItemView(int i) {
        TabView view = new TabView(this);
        view.setIv(R.mipmap.ic_launcher);
        view.setTv(mTextViewArray[i]);
        return view;
    }
}
