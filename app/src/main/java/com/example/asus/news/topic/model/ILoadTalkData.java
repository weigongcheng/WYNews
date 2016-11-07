package com.example.asus.news.topic.model;

import com.example.asus.news.news.OnDataLoadListener;

/**
 * Created by ASUS on 2016/11/3.
 */

public interface ILoadTalkData {
 void getLoadTalkData(String id,OnDataLoadListener onDataLoadListener);
 void getLoadTalkHeadDara(OnDataLoadListener onDataLoadListener);
}
