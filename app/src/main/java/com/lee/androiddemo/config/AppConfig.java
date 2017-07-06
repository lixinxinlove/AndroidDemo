package com.lee.androiddemo.config;

/**
 * Created by android on 2017/3/21.
 */
public class AppConfig {

    public static final boolean DEBUG = true;
    public static String DOUBAN_API_URL = "http://api.douban.com/v2/";
    public static String ZHIHU_API_URL = "http://news-at.zhihu.com/api/4/";
    public static String NETEASY_NEWS_API = "http://c.m.163.com/nc/article/";
    public static String MOVIE_API_URL = "http://api.svipmovie.com/front/";
    public static String GITHUB_API_URL = "https://api.github.com/";

    // 请求失败code
    public static final String HTTP_REQUEST_FAILURE = "-1";
    // 请求成功code
    public static final String HTTP_REQUEST_SUCCESS = "0";
    // 请求数据为空
    public static final String HTTP_REQUEST_EMPTY = "1";
    // json解析失败
    public static final String HTTP_REQUEST_JSON_ERROR = "-2";
}
