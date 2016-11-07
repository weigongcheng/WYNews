package com.example.asus.news.news.model;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/10/30.
 */

public class ITitleDataImpl implements ITitleData {
private String url="http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html";
    @Override
    public void getTitleData(final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder()
                .url(url).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               onDataLoadListener.onLoadFailed(e.getMessage());
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
                 if (response.isSuccessful()){
                     String string = response.body().string();
                     TitleBean titleBean = new Gson().fromJson(string, TitleBean.class);
                     List<TListBean> tList = titleBean.getTList();
                     Collections.sort(tList, new Comparator<TListBean>() {

                         @Override
                         public int compare(TListBean tListBean, TListBean t1) {

                             return   tListBean.getTid().compareTo(t1.getTid());

                         }
                     });
                     onDataLoadListener.onLoadSuccess(titleBean);
                 }
           }
       });

    }
}
