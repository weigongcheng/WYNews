package com.example.asus.news.news.model;

/**
 * Created by ASUS on 2016/10/31.
 */

public  class ADS {
    private String title;
    private String imgsrc;
    private String url;
    public ADS() {
    }

    public ADS(String title, String imgsrc, String url) {
        this.title = title;
        this.imgsrc = imgsrc;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}