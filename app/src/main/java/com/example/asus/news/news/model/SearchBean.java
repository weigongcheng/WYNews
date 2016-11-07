package com.example.asus.news.news.model;

import org.greenrobot.greendao.annotation.Id;

/**
 * Created by ASUS on 2016/11/1.
 */

public class SearchBean {
    private int type;
    private String title;
    private String skipType;
    private String ptime;
    private String id;
    private String imgurl;

    private String tname;

    private String digest;
    private String url;

    public SearchBean(int type, String title, String skipType, String ptime, String id, String imgurl, String tname, String digest, String url) {
        this.type = type;
        this.title = title;
        this.skipType = skipType;
        this.ptime = ptime;
        this.id = id;
        this.imgurl = imgurl;
        this.tname = tname;
        this.digest = digest;
        this.url = url;
    }

    public SearchBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", skipType='" + skipType + '\'' +
                ", ptime='" + ptime + '\'' +
                ", id='" + id + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", tname='" + tname + '\'' +
                ", digest='" + digest + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
