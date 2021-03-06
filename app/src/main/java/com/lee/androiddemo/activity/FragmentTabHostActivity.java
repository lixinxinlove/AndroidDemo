package com.lee.androiddemo.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import com.lee.androiddemo.R;
import com.lee.androiddemo.fragment.FlexBoxFragment;
import com.lee.androiddemo.fragment.HomeFragment;
import com.lee.androiddemo.fragment.MyFragment;
import com.lee.androiddemo.fragment.ViewFragment;
import com.lee.androiddemo.icon.IconValues;
import com.lee.androiddemo.rxbus.Action;
import com.lee.androiddemo.rxbus.RxBus;
import com.lee.androiddemo.view.TabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentTabHostActivity extends BaseActivity {

    @BindView(R.id.fragment_tab_host)
    FragmentTabHost mTabHost;
    //定义数组存放Fragment
    private final Class fragmentArray[] = {MyFragment.class, HomeFragment.class, FlexBoxFragment.class, ViewFragment.class};
    private int mImageViewArray[] = IconValues.homeTabArr;
    private String mTextViewArray[] = IconValues.homeTabTextArr;
    private String mTabIdArray[] = {"home", "lll", "eee", "my"};

    List<Observer<Action>> listS = new ArrayList<>();
    Observer<Action> rxSbscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "font/bebasneue_webfont.ttf");
        //revenueSum.setTypeface(typeFace);


        rxSbscription = RxBus.getDefault().toObservable(Action.class).subscribeWith(new Observer<Action>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Action value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


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
        mTabHost.getTabWidget().setDividerDrawable(R.color.tr);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
