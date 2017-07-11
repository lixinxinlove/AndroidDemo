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


    public void test(RequestCallback callback) {
        String url = "http://mallapi.ecoology.cn/v1/Product/Detail?standardId=100000001073&refreshToken=LmqvNOsjYcouPHnMykfKTNYHRoEkKLTvSiRtrbNImOwYFyPEFMJeyzwppaqPSkqs";
        http.getData(url, callback);


    }


}
