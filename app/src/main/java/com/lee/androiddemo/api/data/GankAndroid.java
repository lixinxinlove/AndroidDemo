package com.lee.androiddemo.api.data;

import java.util.List;

/**
 * Created by android on 2017/7/11.
 */

public class GankAndroid {

    /**
     * error : false
     * results : [{"_id":"595f7f6d421aa90ca209c416","createdAt":"2017-07-07T20:32:45.22Z","desc":"根据实际开发中遇到的需求，使用 Gradle 对应用的不同版本进行个性化定制。","images":["http://img.gank.io/0be70b9b-dc5a-4778-bb7b-0f5ff0e73d2a"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"http://www.imliujun.com/gradle1.html","used":true,"who":"LiuJun"},{"_id":"5964263e421aa90ca3bb6adc","createdAt":"2017-07-11T09:13:34.550Z","desc":"你的Android应用稳定吗？","publishedAt":"2017-07-11T13:46:33.911Z","source":"web","type":"Android","url":"http://url.cn/4BbWlxC","used":true,"who":"陈宇明"},{"_id":"59646466421aa90c9203d367","createdAt":"2017-07-11T13:38:46.38Z","desc":"Android 信用卡交易效果 UI 。","images":["http://img.gank.io/b9a34460-8224-449d-903d-3ef54a3f35b6"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/KingsMentor/Luhn","used":true,"who":"代码家"},{"_id":"59646491421aa90ca209c433","createdAt":"2017-07-11T13:39:29.898Z","desc":"效果很棒的 Fab 按钮。","images":["http://img.gank.io/221dc9b8-f3cb-4602-8a52-43a780328925"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/jahirfiquitiva/FABsMenu","used":true,"who":"代码家"},{"_id":"595f9710421aa90ca3bb6abf","createdAt":"2017-07-07T22:13:36.139Z","desc":"基于ASM，通过注解，实现对方法调用时的参数、返回值、耗时等信息的纪录。","publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/saymagic/Daffodil","used":true,"who":"saymagic"},{"_id":"5960ede0421aa90cb4724b95","createdAt":"2017-07-08T22:36:16.905Z","desc":"Android之重新推导设备尺寸","images":["http://img.gank.io/666f8d19-4d94-4d14-bfc8-5ff516777f4b"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/3475c0006948","used":true,"who":"bolex"},{"_id":"5962e177421aa90c9203d358","createdAt":"2017-07-10T10:07:51.286Z","desc":"懂得智能配色的ImageView,还能给自己设置多彩的阴影哦。","images":["http://img.gank.io/4cc10e1d-fb75-4c22-9df9-091706b55c82"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/DingMouRen/PaletteImageView-","used":true,"who":null},{"_id":"5962e66f421aa90ca3bb6ad2","createdAt":"2017-07-10T10:29:03.275Z","desc":"基于Glide V4.0封装的图片加载库，可以监听加载图片时的进度","publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/sfsheng0322/GlideImageView","used":true,"who":"孙福生"},{"_id":"5962f947421aa90ca209c427","createdAt":"2017-07-10T11:49:27.301Z","desc":"Android Interview Questions","images":["http://img.gank.io/fa5e232e-ee7b-4ef5-8c1f-2b0197dfafb6"],"publishedAt":"2017-07-10T12:48:49.297Z","source":"web","type":"Android","url":"https://github.com/MindorksOpenSource/android-interview-questions","used":true,"who":"AMIT SHEKHAR"},{"_id":"595df22b421aa90ca3bb6ab4","createdAt":"2017-07-06T16:17:47.759Z","desc":"多进程安全的SharedPreferences","publishedAt":"2017-07-07T12:14:57.685Z","source":"chrome","type":"Android","url":"https://github.com/grandcentrix/tray","used":true,"who":"galois"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 595f7f6d421aa90ca209c416
         * createdAt : 2017-07-07T20:32:45.22Z
         * desc : 根据实际开发中遇到的需求，使用 Gradle 对应用的不同版本进行个性化定制。
         * images : ["http://img.gank.io/0be70b9b-dc5a-4778-bb7b-0f5ff0e73d2a"]
         * publishedAt : 2017-07-11T13:46:33.911Z
         * source : chrome
         * type : Android
         * url : http://www.imliujun.com/gradle1.html
         * used : true
         * who : LiuJun
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }
}
