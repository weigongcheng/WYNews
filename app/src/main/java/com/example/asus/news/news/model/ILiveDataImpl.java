package com.example.asus.news.news.model;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/11/5.
 */

public class ILiveDataImpl implements ILiveData {
    private  String url="http://c.m.163.com/recommend/getChanListNews?\n" +
            "channel=T1457068979049&size=20&offset=0&fn=1&passport=&devId=IvywnJ%2BQQSjPGVxCMeAhww%3D\n" +
            "%3D&lat=ZB2eQPGNyfA31Un5k5kVPg%3D%3D&lon=MmRSMfUu%2Fg0ODQzJ7yHgxg%3D\n" +
            "%3D&version=17.1&net=wifi&ts=1478273931&sign=lhSxOVt6rLv5zh8anIFX5%2FkuCN3Rmi\n" +
            "%2Fd1O5HcwGPosp48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=vAO9EPgi0MjYbFe\n" +
            "%2FJ5lR5chl6XBe73qotuvJso3%2B3s0%3D";
    @Override
    public void getLiveData( final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder().url(url).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
      client.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              onDataLoadListener.onLoadFailed(e.getMessage());
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              if (response.isSuccessful()){
                  String string = response.body().string();
                  SpBean bean = new Gson().fromJson(string, SpBean.class);
                  onDataLoadListener.onLoadSuccess(bean);
              }else{
                  onDataLoadListener.onLoadSuccess(null);
              }

          }
      });
    }
}
