package com.example.asus.news.live.model;

import com.example.asus.news.news.OnDataLoadListener;

/**
 * Created by ASUS on 2016/11/2.
 */

public interface IDLiveData  {
    void getLiveDetailsData(String id,OnDataLoadListener onDataLoadListener);
}
