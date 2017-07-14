package com.lee.androiddemo.http;

import android.os.Handler;
import android.os.Looper;

import com.lee.androiddemo.config.AppConfig;
import com.lee.androiddemo.utils.GsonUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataApiForOkHttp {

    public OkHttpClient http;

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public DataApiForOkHttp() {

        http = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public void get(String url, RequestCallback callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method(GET_METHOD, null);
        Request request = requestBuilder.build();
        Call mCall = http.newCall(request);
        callback.setmCall(mCall);
        mCall.enqueue(new EventRequestCallbackImpl(callback));
    }

    public void post(FormBody body, String url, RequestCallback callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //Builder builder = new Builder();
        //FormBody body = builder.add("", "").build();
        requestBuilder.post(body).build();
        Request request = requestBuilder.post(body).build();
        Call mCall = http.newCall(request);
        mCall.enqueue(new EventRequestCallbackImpl(callback));
    }


    public void getData(String url, RequestCallback callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        requestBuilder.method(GET_METHOD, null);
        Request request = requestBuilder.build();
        Call mCall = http.newCall(request);
        callback.setmCall(mCall);
        mCall.enqueue(new MyEventRequestCallbackImpl(callback));
    }


    /**
     * 返回结果在io线程
     *
     * @param body
     * @param url
     * @param callback
     */
    public void postAsync(FormBody body, String url, RequestCallback callback) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        //Builder builder = new Builder();
        //FormBody body = builder.add("", "").build();
        requestBuilder.post(body).build();
        Request request = requestBuilder.post(body).build();
        Call mCall = http.newCall(request);
        mCall.enqueue(new EventRequestCallbackAsyncImpl(callback));
    }


    /**
     * 在UI线程返回
     */
    public class EventRequestCallbackImpl implements Callback {

        private RequestCallback mCallback;

        private ResponseEntity resEntity = new ResponseEntity();

        public EventRequestCallbackImpl(RequestCallback callback) {
            this.mCallback = callback;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onStart();
                }
            });
        }

        @Override
        public void onFailure(Call arg0, IOException arg1) {
            resEntity.code = AppConfig.HTTP_REQUEST_FAILURE;
            resEntity.message = "网络异常";
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.RequestCallback(resEntity);
                }
            });
        }

        @Override
        public void onResponse(Call arg0, Response response) throws IOException {
            if (response.isSuccessful()) {
                String res = response.body().string();
                try {
                    resEntity = GsonUtils.asJSONToResponseEntity(res);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.RequestCallback(resEntity);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    resEntity.code = AppConfig.HTTP_REQUEST_JSON_ERROR;
                    resEntity.message = "数据格式错误";
                    resEntity.data = res;
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            mCallback.RequestCallback(resEntity);
                        }
                    });
                }
            }
        }
    }

    /**
     * 返回数据不解析
     */
    public class MyEventRequestCallbackImpl implements Callback {

        private RequestCallback mCallback;

        private ResponseEntity resEntity = new ResponseEntity();

        public MyEventRequestCallbackImpl(RequestCallback callback) {
            this.mCallback = callback;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onStart();
                }
            });
        }


        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res = response.body().string();
            resEntity.code = AppConfig.HTTP_REQUEST_SUCCESS;
            resEntity.data = res;
            resEntity.message = "执行成功";

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.RequestCallback(resEntity);
                }
            });
        }

        @Override
        public void onFailure(Call call, IOException e) {
            resEntity.code = AppConfig.HTTP_REQUEST_FAILURE;
            resEntity.data = "";
            resEntity.message = "网络异常";
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.RequestCallback(resEntity);
                }
            });
        }
    }


    /**
     * 异步返回数据
     */
    public class EventRequestCallbackAsyncImpl implements Callback {

        private RequestCallback mCallback;

        private ResponseEntity resEntity = new ResponseEntity();

        public EventRequestCallbackAsyncImpl(RequestCallback callback) {
            this.mCallback = callback;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onStart();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                String res = response.body().string();
                try {
                    resEntity = GsonUtils.asJSONToResponseEntity(res);
                    mCallback.RequestCallback(resEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    resEntity.code = AppConfig.HTTP_REQUEST_JSON_ERROR;
                    resEntity.message = "数据格式错误";
                    resEntity.data = res;
                    mCallback.RequestCallback(resEntity);
                }
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            resEntity.code = AppConfig.HTTP_REQUEST_FAILURE;
            resEntity.data = "";
            resEntity.message = "网络异常";
            mCallback.RequestCallback(resEntity);
        }
    }


}
