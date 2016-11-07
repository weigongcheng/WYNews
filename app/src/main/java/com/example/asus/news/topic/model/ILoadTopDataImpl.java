package com.example.asus.news.topic.model;

import android.util.Log;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/11/3.
 */

public class ILoadTopDataImpl implements ILoadTopData {
    private String topUrl="http://c.m.163.com/newstopic/list/classification.html";
    private String MainUrl="http://c.m.163.com/newstopic/list/expert/%s/%s.html";
    @Override
    public void getLoadTopData(final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder().url(topUrl).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
         if (response.isSuccessful()){
             String string = response.body().string();
             Log.d("zanZQ", "onResponse:qqq "+string);
             List<WbTopBean> list=new ArrayList<WbTopBean>();
             try {
                 JSONObject jsonObject = new JSONObject(string);
                 JSONArray data = jsonObject.getJSONArray("data");
                 for (int i = 0; i < data.length(); i++) {
                     JSONObject object = data.getJSONObject(i);
                     String id = object.getString("id");
                     String name = object.getString("name");
                     String icon = object.getString("icon");
                     String picurl = object.getString("picurl");
                     int createTime = object.getInt("createTime");
                     int updateTime = object.getInt("updateTime");
                     list.add(new WbTopBean(id,name,icon,picurl,createTime,updateTime));
                 }
                 onDataLoadListener.onLoadSuccess(list);
             } catch (JSONException e) {
                 e.printStackTrace();
             }

         }else{
             onDataLoadListener.onLoadSuccess(null);
         }
            }
        });
    }

    @Override
    public void getLoadWbMainData(String type, String page, final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        if (type==null){
            type="5bm%2F5bee";
        }
        Request request=new Request.Builder().url(String.format(MainUrl,type,page)).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    WbMainBean wbMainBean = new Gson().fromJson(string, WbMainBean.class);
                    onDataLoadListener.onLoadSuccess(wbMainBean);
                }else{
                  onDataLoadListener.onLoadSuccess(null);
                }
            }
        });
    }
}
