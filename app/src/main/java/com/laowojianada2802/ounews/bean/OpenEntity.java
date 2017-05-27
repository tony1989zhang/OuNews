package com.laowojianada2802.ounews.bean;

/**
 * Created by Administrator on 2017/5/18 0018.
 */


public class OpenEntity {

    public int status;
    public MsgBean msg;
    public String url;

    public static class MsgBean {
        public String id;
        public String mcih;
        public String name;
        public int open;
        public String links;

        @Override
        public String toString() {
            return "MsgBean{" +
                    "id='" + id + '\'' +
                    ", mcih='" + mcih + '\'' +
                    ", name='" + name + '\'' +
                    ", open='" + open + '\'' +
                    ", links='" + links + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OpenEntity{" +
                "status=" + status +
                ", msg=" + msg +
                ", url='" + url + '\'' +
                '}';
    }
}
