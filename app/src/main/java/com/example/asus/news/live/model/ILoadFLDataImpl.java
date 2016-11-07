package com.example.asus.news.live.model;

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
 * Created by ASUS on 2016/11/2.
 */

public class ILoadFLDataImpl implements ILoadFLData {
    private String HeadUrl="http://data.live.126.net/livechannel/classifylist.json";
    private String MainUrl="http://data.live.126.net/livechannel/classify/%s/%s.json ";
    private String HotMainUrl="http://data.live.126.net/livechannel/previewlist/%s.json";
    @Override
    public void getLoadFLHeadData(final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder().url(HeadUrl).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    Log.d("zanZQ", "onResponse: "+string);
                  List<FLHeadBean> list= pareJson(string);
                    onDataLoadListener.onLoadSuccess(list);
                }else{
                    onDataLoadListener.onLoadSuccess(null);
                }

            }
        });

    }

    @Override
    public void getLoadFLMainData(String id, String page, final OnDataLoadListener onDataLoadListener) {
        Request request=new Request.Builder().url(String.format(MainUrl,id,page)).build();
        OkHttpClient client = NetUtil.getClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if (response.isSuccessful()){
                  String string = response.body().string();
                  Log.d("zanZQ", "onResponse: "+string);
                  FLMainBean flMainBean = new Gson().fromJson(string, FLMainBean.class);
                  onDataLoadListener.onLoadSuccess(flMainBean);
              }else{
                  onDataLoadListener.onLoadSuccess(null);
              }
            }
        });
    }

    @Override
    public void getLoadHotMainData(final String id, final OnDataLoadListener onDataLoadListener) {
        Request request=new Request.Builder().url(String.format(HotMainUrl,id)).build();
           NetUtil.getClient().newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {
                   onDataLoadListener.onLoadFailed(e.getMessage());
               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    Object object = new Object();
                    if (id.equals("1")){
                        object = new Gson().fromJson(string, HotMainFirstBean.class);
                      }else{
                        object = new Gson().fromJson(string, FLMainBean.class);
                      }
                    onDataLoadListener.onLoadSuccess(object);
                }else{
                    onDataLoadListener.onLoadSuccess(null);
                }
               }
           });
    }

    private List<FLHeadBean> pareJson(String string) {
        List<FLHeadBean> list=new ArrayList<>();
        try {
            JSONArray array = new JSONArray(string);
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);
                int order = data.getInt("order");
                int id = data.getInt("id");
                String type = data.getString("type");
                String name = data.getString("name");
                boolean visible = data.getBoolean("visible");
                FLHeadBean flHeadBean = new FLHeadBean(order, id, type, name, visible);
                list.add(flHeadBean);

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
