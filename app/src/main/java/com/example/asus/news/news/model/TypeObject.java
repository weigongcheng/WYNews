package com.example.asus.news.news.model;

import java.util.List;

/**
 * Created by ASUS on 2016/11/5.
 */

public class TypeObject {
    private int type;
    private List<PicBean> picBeanList;
    private List<BodyBean> bodyBeanList;

    public TypeObject() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<PicBean> getPicBeanList() {
        return picBeanList;
    }

    public void setPicBeanList(List<PicBean> picBeanList) {
        this.picBeanList = picBeanList;
    }

    public List<BodyBean> getBodyBeanList() {
        return bodyBeanList;
    }

    public void setBodyBeanList(List<BodyBean> bodyBeanList) {
        this.bodyBeanList = bodyBeanList;
    }
}
