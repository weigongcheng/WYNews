package com.example.asus.news.live.model;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.TListBean;

/**
 * Created by ASUS on 2016/11/2.
 */

public interface ILoadFLData {
    void getLoadFLHeadData(OnDataLoadListener onDataLoadListener);
    void getLoadFLMainData(String id,String page,OnDataLoadListener onDataLoadListener);
    void getLoadHotMainData(String id,OnDataLoadListener onDataLoadListener);
}
