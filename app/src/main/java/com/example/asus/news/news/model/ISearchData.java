package com.example.asus.news.news.model;

import com.example.asus.news.news.OnDataLoadListener;

/**
 * Created by ASUS on 2016/11/1.
 */

public interface ISearchData {
    void getSearchData(String keywords, OnDataLoadListener onDataLoadListener);
    void getSearchResultData(String url,String id,int type ,OnDataLoadListener onDataLoadListener);
}
