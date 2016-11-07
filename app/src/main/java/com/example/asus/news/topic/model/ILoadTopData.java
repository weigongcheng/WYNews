package com.example.asus.news.topic.model;

import com.example.asus.news.news.OnDataLoadListener;

/**
 * Created by ASUS on 2016/11/3.
 */

public interface ILoadTopData {
    void getLoadTopData(OnDataLoadListener onDataLoadListener);
    void getLoadWbMainData(String type,String page,OnDataLoadListener onDataLoadListener);
}
