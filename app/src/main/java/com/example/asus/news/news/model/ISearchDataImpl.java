package com.example.asus.news.news.model;

import android.util.Base64;
import android.util.Log;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.example.asus.news.util.ParseJsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/11/1.
 */

public class ISearchDataImpl implements ISearchData {
    public final static int doc=0;
    public final static int video=1;
    public final static int photoset=2;
    //private String url="http://c.3g.163.com/search/comp/MA%3D%3D/20/%s.html?deviceId=ODY3NTE2MDI0MDQ0Mjg4&version=bmV3c2NsaWVudC4xNy4wLmFuZHJvaWQ%3D&channel=VDEzNDg2NDc5MDkxMDc%3D";
    @Override
    public void getSearchData(String keywords, final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        byte[] bytes = Base64.encode(keywords.getBytes(), Base64.DEFAULT);
        String key=new String(bytes,0,bytes.length);
       key= key.substring(0,key.lastIndexOf("\n"));
        String url = null;
//        try {
            url = "http://c.3g.163.com/search/comp/MA%3D%3D/20/"+ key +".html?deviceId=ODY3NTE2MDI0MDQ0Mjg4&version=bmV3c2NsaWVudC4xNy4wLmFuZHJvaWQ%3D&channel=VDEzNDg2NDc5MDkxMDc%3D";
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Request request=new Request.Builder().url(url).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
       // Log.d("zanZQ", "getSearchData: "+url);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 if (response.isSuccessful()){
                     String string = response.body().string();
                     string=string.replace("<em>","").replace("</em>","");
                //     Log.d("zanZQ", "onResponse: "+string);
                     List<SearchBean> list = ParseJsonUtil.getSearchBeanListByJson(string);
                     onDataLoadListener.onLoadSuccess(list);
                 }
            }
        });
    }

    @Override
    public void getSearchResultData(String url, final String id, final int type , final OnDataLoadListener onDataLoadListener) {
        Request request=new Request.Builder().url(url).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        NetUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String url=null;
              if (response.isSuccessful()){
                  String string = response.body().string();
                  try {
                  switch (type){
                     case doc:
                         url= new JSONObject(string).getJSONObject(id).getString("shareLink");
                            onDataLoadListener.onLoadSuccess(url);
                         break;
                     case video:
                         url = new JSONObject(string).getString("mp4_url");
                         onDataLoadListener.onLoadSuccess(url);
                         break;
                     case photoset:
                         url = new JSONObject(string).getString("url");
                         onDataLoadListener.onLoadSuccess(url);
                         break;
                 }
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
            }else{
                  onDataLoadListener.onLoadSuccess(null);
              }
                Log.d("zanZQ", "onResponse: "+url);

            }
        });
    }
}
