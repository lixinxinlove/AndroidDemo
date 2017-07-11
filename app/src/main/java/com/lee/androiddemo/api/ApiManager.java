package com.lee.androiddemo.api;

import com.lee.androiddemo.config.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 集中处理Api相关配置的Manager类
 */
public class ApiManager {


    private MovieApi mMovieApi;

    private GankIO mGankIO;

    private static ApiManager sApiManager;

    private static OkHttpClient mClient;

    private ApiManager() {

    }

    public static ApiManager getInstence() {
        if (sApiManager == null) {
            synchronized (ApiManager.class) {
                if (sApiManager == null) {
                    sApiManager = new ApiManager();
                }
            }
        }
        mClient = new OkHttpClient.Builder()
                // .addInterceptor(new CustomInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return sApiManager;
    }


    /**
     * 封装视频 API
     */
    public MovieApi getMovieService() {
        if (mMovieApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.MOVIE_API_URL)
                    .client(mClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mMovieApi = retrofit.create(MovieApi.class);
        }
        return mMovieApi;
    }


    /**
     * 封装干货API
     */
    public GankIO getGankService() {
        if (mGankIO == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://gank.io/api/data/")
                    .client(mClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mGankIO = retrofit.create(GankIO.class);
        }
        return mGankIO;
    }

}
