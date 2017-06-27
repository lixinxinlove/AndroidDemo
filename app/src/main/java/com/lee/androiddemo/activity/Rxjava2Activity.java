package com.lee.androiddemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.androiddemo.R;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class Rxjava2Activity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "Rxjava2Activity";
    @BindView(R.id.btn_rx)
    Button btnRx;
    Observable<Integer> observable;
    Observable<Integer> observable2;
    Observer<Integer> observer;
    Observer<String> observer2;

    @Override
    protected int getContextView() {
        return R.layout.activity_rxjava2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建一个上游 Observable：
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                for (int i = 0; i < 100000; i++) {
                    if (i % 50 == 0) {
                        emitter.onNext(i);
                    }
                }
            }
        });

        //创建一个上游 Observable：
        observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 100000; i++) {
                    if (i % 50 == 0) {
                        emitter.onNext(i);

                    }
                }

            }
        });


        //创建一个下游 Observer
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "complete");
            }
        };


        observer2 = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "" + value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


    }


    @Override
    protected void initListener() {

        btnRx.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rx:
                //建立连接
//                observable.map(new Function<Integer, Integer>() {
//                    @Override
//                    public Integer apply(Integer integer) throws Exception {
//                        return integer + 1;
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);


                Observable.zip(observable, observable2, new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Exception {
                        return integer + "--" + integer2;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer2);


                break;
            default:
                break;
        }
    }
}
