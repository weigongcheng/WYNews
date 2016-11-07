package com.example.asus.news.live.model;

import android.util.Log;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/11/2.
 */

public class IDLiveDataImpl implements IDLiveData {
 private   String url="http://data.live.126.net/liveAll/%s.json";
    @Override
    public void getLiveDetailsData(String id, final OnDataLoadListener onDataLoadListener) {
        String format = String.format(url, id);
        Log.d("zanZQ", "getLiveDetailsData: "+format);
        Request request=new Request.Builder().url(format).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        NetUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   if (response.isSuccessful()){
                       String string = response.body().string();
                       LiveDetailsBean liveDetailsBean = new Gson().fromJson(string, LiveDetailsBean.class);
                       onDataLoadListener.onLoadSuccess(liveDetailsBean);
                   }else{
                       onDataLoadListener.onLoadSuccess(null);
                   }
            }
        });
    }
}
