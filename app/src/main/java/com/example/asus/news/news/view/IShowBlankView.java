package com.example.asus.news.news.view;

import com.example.asus.news.news.model.BodyBean;
import com.example.asus.news.news.model.PicBean;

import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public interface IShowBlankView {
    void ShowBlankView(List<BodyBean> list);
    void ShowBlankPicView(List<PicBean> picBeanList);
}
