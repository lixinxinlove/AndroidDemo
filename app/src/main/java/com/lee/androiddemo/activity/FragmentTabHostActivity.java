package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import com.lee.androiddemo.R;
import com.lee.androiddemo.fragment.FlexboxFragment;
import com.lee.androiddemo.fragment.HomeFragment;
import com.lee.androiddemo.fragment.MyFragment;
import com.lee.androiddemo.icon.IconValues;
import com.lee.androiddemo.view.TabView;

import butterknife.BindView;

public class FragmentTabHostActivity extends BaseActivity {

    @BindView(R.id.fragment_tab_host)
    FragmentTabHost mTabHost;
    //定义数组存放Fragment
    private final Class fragmentArray[] = {MyFragment.class, HomeFragment.class, FlexboxFragment.class, MyFragment.class};
    private int mImageViewArray[] = IconValues.homeTabArr;
    private String mTextViewArray[] = IconValues.homeTabTextArr;
    private String mTabIdArray[] = {"home", "lll", "eee", "my"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContextView() {
        return R.layout.activity_fragment_tab_host;
    }

    @Override
    protected void initListener() {
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.e("tab", tabId);
            }
        });
    }

    @Override
    protected void initData() {
        initFragment();
    }

    /**
     * 初始化Fragment
     */

    private void initFragment() {

        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);//设置替换哪个布局
        int fragmentCount = fragmentArray.length;

        for (int i = 0; i < fragmentCount; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabIdArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            Bundle bundle = new Bundle();
            bundle.putString("title", "lee");
            mTabHost.addTab(tabSpec, fragmentArray[i], bundle);
        }
    }

    /**
     * 设置每个Item的布局
     *
     * @return
     */
    private View getTabItemView(int i) {
        TabView view = new TabView(this);
        view.setIv(mImageViewArray[i]);
        view.setTv(mTextViewArray[i]);
        return view;
    }
}
