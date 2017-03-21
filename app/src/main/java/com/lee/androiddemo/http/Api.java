package com.lee.androiddemo.http;

/**
 * Created by lixinxin on 2017/3/21.
 */

public class Api {
    DataApiForOkHttp http = new DataApiForOkHttp();

    public void login(RequestCallback callback) {
        String url = "http://www.baidu.com";
        http.getData(url, callback);
    }

}
