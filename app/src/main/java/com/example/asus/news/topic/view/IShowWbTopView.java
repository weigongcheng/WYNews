package com.example.asus.news.topic.view;

import com.example.asus.news.topic.model.WbMainBean;
import com.example.asus.news.topic.model.WbTopBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public interface IShowWbTopView {
    void getTopDataView(List<WbTopBean> list);
    void getMainDataView(WbMainBean wbMainBean);
}
