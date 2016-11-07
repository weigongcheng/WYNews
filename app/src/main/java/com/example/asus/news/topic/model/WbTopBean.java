package com.example.asus.news.topic.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2016/11/3.
 */
@Entity
public class WbTopBean {

    /**
     * id : law
     * name : 法律
     * icon : http://img1.cache.netease.com/m//newsapp/subject/icon/fl.png
     * picurl : http://img1.cache.netease.com/m//newsapp/subject/fl.png
     * createTime : 1
     * updateTime : 15
     */
@Property
    private String id;
    @Property
    private String name;
    @Property
    private String icon;
    @Property
    private String picurl;
    @Property
    private int createTime;
    @Property
    private int updateTime;

    @Generated(hash = 1836703305)
    public WbTopBean(String id, String name, String icon, String picurl,
            int createTime, int updateTime) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.picurl = picurl;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1249649333)
    public WbTopBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
