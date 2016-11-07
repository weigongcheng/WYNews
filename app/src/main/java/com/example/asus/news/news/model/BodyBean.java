package com.example.asus.news.news.model;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public class BodyBean {
  private String title;
    private  List<ADS> list;
    private String source;
    private String imgsrc;
    private String ptime;
    private List<String> imgextra;
    private String url;
    private String digest;
    private int Type;
    private String skipID;
    private String docid;
    private String tag;
    private String user_count;
    private String videoID;

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    private boolean isEditor;

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }

    public int getType() {
        return Type;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public void setType(int type) {
        Type = type;
    }

    public BodyBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ADS> getList() {
        return list;
    }

    public void setList(List<ADS> list) {
        this.list = list;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<String> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<String> imgextra) {
        this.imgextra = imgextra;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public BodyBean(String title, List<ADS> list, String source, String imgsrc, String ptime, List<String> imgextra, String url, String digest) {
        this.title = title;
        this.list = list;
        this.source = source;
        this.imgsrc = imgsrc;
        this.ptime = ptime;
        this.imgextra = imgextra;
        this.url = url;
        this.digest = digest;
    }
}
