package com.example.asus.news.news.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by ASUS on 2016/10/31.
 */

@Entity
public  class TListBean {
    @Property
    private String template;
    @Property
    private String topicid;
    @Property
    private boolean hasCover;
    @Property
    private String alias;
    @Property
    private String subnum;
    @Property
    private int recommendOrder;
    @Property
    private int isNew;
    @Property
    private int hashead;
    @Property
    private String img;
    @Property
    private int isHot;
    @Property
    private boolean hasIcon;
    @Property
    private String cid;
    @Property
    private String recommend;
    @Property
    private boolean headLine;
    @Property
    private int hasAD;
    @Property
    private String color;
    @Property
    private int bannerOrder;
    @Property
    private String tname;
    @Property
    private String ename;
    @Property
    private String showType;
    @Property
    private int special;
    @Id
    private String tid;
    @Property
    private  boolean IsSelected;

    public boolean isSelected() {
        return IsSelected;
    }

    public void setSelected(boolean selected) {
        IsSelected = selected;
    }

    @Generated(hash = 1530783620)
    public TListBean(String template, String topicid, boolean hasCover,
            String alias, String subnum, int recommendOrder, int isNew, int hashead,
            String img, int isHot, boolean hasIcon, String cid, String recommend,
            boolean headLine, int hasAD, String color, int bannerOrder,
            String tname, String ename, String showType, int special, String tid,
            boolean IsSelected) {
        this.template = template;
        this.topicid = topicid;
        this.hasCover = hasCover;
        this.alias = alias;
        this.subnum = subnum;
        this.recommendOrder = recommendOrder;
        this.isNew = isNew;
        this.hashead = hashead;
        this.img = img;
        this.isHot = isHot;
        this.hasIcon = hasIcon;
        this.cid = cid;
        this.recommend = recommend;
        this.headLine = headLine;
        this.hasAD = hasAD;
        this.color = color;
        this.bannerOrder = bannerOrder;
        this.tname = tname;
        this.ename = ename;
        this.showType = showType;
        this.special = special;
        this.tid = tid;
        this.IsSelected = IsSelected;
    }

    @Generated(hash = 1989706801)
    public TListBean() {
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSubnum() {
        return subnum;
    }

    public void setSubnum(String subnum) {
        this.subnum = subnum;
    }

    public int getRecommendOrder() {
        return recommendOrder;
    }

    public void setRecommendOrder(int recommendOrder) {
        this.recommendOrder = recommendOrder;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getHashead() {
        return hashead;
    }

    public void setHashead(int hashead) {
        this.hashead = hashead;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public boolean isHeadLine() {
        return headLine;
    }

    public void setHeadLine(boolean headLine) {
        this.headLine = headLine;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBannerOrder() {
        return bannerOrder;
    }

    public void setBannerOrder(int bannerOrder) {
        this.bannerOrder = bannerOrder;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public boolean getHasCover() {
        return this.hasCover;
    }

    public boolean getHasIcon() {
        return this.hasIcon;
    }

    public boolean getHeadLine() {
        return this.headLine;
    }

    public boolean getIsSelected() {
        return this.IsSelected;
    }

    public void setIsSelected(boolean IsSelected) {
        this.IsSelected = IsSelected;
    }
}