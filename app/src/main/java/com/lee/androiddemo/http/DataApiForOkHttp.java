package com.lee.androiddemo.http;

import android.os.Handler;
import android.os.Looper;

import com.lee.androiddemo.config.AppConfig;
import com.lee.androiddemo.utils.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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


    //一种：参数请求体
//    FormBody paramsBody=new FormBody.Builder()
//            .add("id",currentPlan.getPlanId()+"")
//            .add("name",currentPlan.getName())
//            .add("volume",currentPlan.getVolume())
//            .add("type",currentPlan.getType()+"")
//            .add("mode",currentPlan.getMode()+"")
//            .build();


    //二种：文件请求体
    MediaType type = MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
    File file = new File("/data/data/com.example.company/files/plan/plans.xml");
    RequestBody fileBody = RequestBody.create(type, file);


    //三种：混合参数和文件请求
//    RequestBody multipartBody = new MultipartBody.Builder()
//            .setType(MultipartBody.ALTERNATIVE)
//            //一样的效果
//            .addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"params\"")
//                    ,paramsBody)
//            .addPart(Headers.of(
//                    "Content-Disposition",
//                    "form-data; name=\"file\"; filename=\"plans.xml\"")
//                    , fileBody)
    //一样的效果
        /*.addFormDataPart("id",currentPlan.getPlanId()+"")
        .addFormDataPart("name",currentPlan.getName())
        .addFormDataPart("volume",currentPlan.getVolume())
        .addFormDataPart("type",currentPlan.getType()+"")
        .addFormDataPart("mode",currentPlan.getMode()+"")
        .addFormDataPart("params","plans.xml",fileBody)*/
    //    .build();


//
//    Request request=new Request.Builder().url("http://192.168.1.121:8080/Server/Service")
//            .addHeader("User-Agent","android")
//            .header("Content-Type","text/html; charset=utf-8;")
//            .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
//            .build();
//
//     client.newCall(request).enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//
//            Log.i("xxx","1、连接的消息"+response.message());
//            if(response.isSuccessful()){
//                Log.i("xxx","2、连接成功获取的内容"+response.body().string());
//            }
//        }
//    });


//    /**
//     * 登录
//     *
//     * @param chimelongCallback
//     * @param username
//     * @param password
//     */
//    public void login(EventRequestCallback chimelongCallback, String username, String password) {
//        TreeMap<String, String> tree = createBaseParams();
//        String url = createGetURIByInterface(tree, "e/auth/index?");
//        Builder builder = new Builder();
//        FormBody body = builder
//                .add("username", username)
//                .add("password", password)
//                .add("app_id", Config.REQUEST_APPID_VALUE)
//                .build();
//        okHttp.post(body, url, chimelongCallback);
//    }




}
