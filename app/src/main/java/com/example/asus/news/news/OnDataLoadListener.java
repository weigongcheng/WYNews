package com.example.asus.news.news;

/**
 * Created by ASUS on 2016/10/30.
 */

public interface OnDataLoadListener {
    void onLoadSuccess(Object object);

    void onLoadFailed(String errorMsg);
}
