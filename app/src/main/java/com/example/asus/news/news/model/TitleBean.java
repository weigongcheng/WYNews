package com.example.asus.news.news.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2016/10/30.
 */

public class TitleBean  {

    /**
     * template : normal1
     * topicid : 000181S1
     * hasCover : false
     * alias : The Truth
     * subnum : 超过1000万
     * recommendOrder : 0
     * isNew : 0
     * hashead : 1
     * img : http://img2.cache.netease.com/m/newsapp/banner/zhenhua.png
     * isHot : 0
     * hasIcon : true
     * cid : C1348654575297
     * recommend : 0
     * headLine : false
     * hasAD : 1
     * color :
     * bannerOrder : 105
     * tname : 独家
     * ename : zhenhua
     * showType : comment
     * special : 0
     * tid : T1370583240249
     */

    private List<TListBean> tList;

    public List<TListBean> getTList() {
        return tList;
    }

    public void setTList(List<TListBean> tList) {
        this.tList = tList;
    }
   private TitleBean titleBean;

    public TitleBean getTitleBean() {
        return titleBean;
    }

    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
    }
}
