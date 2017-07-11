package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;
import com.lee.androiddemo.api.ApiManager;
import com.lee.androiddemo.api.data.MovieResponse;
import com.lee.androiddemo.api.data.RetDataBean;
import com.lee.androiddemo.view.GestureLockView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GestureLockActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "GestureLockActivity";

    private Observable<MovieResponse<RetDataBean>> observable;


    @BindView(R.id.gesture_lock_view)
    GestureLockView lockView;

    @BindView(R.id.btn_rx_rt)
    Button btnRxRt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnRxRt.setOnClickListener(this);

    }

    private void rxhttp() {
        observable = ApiManager.getInstence().getMovieService().getHomePage();

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieResponse<RetDataBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieResponse<RetDataBean> value) {
                Log.e("onNext", value.getMsg());
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
        return R.layout.activity_gesture_lock;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

        lockView.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String gestureCode) {
                Log.e(TAG, gestureCode);
            }
        });
    }

    @Override
    public void onClick(View v) {
        rxhttp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observable = null;
    }
}
