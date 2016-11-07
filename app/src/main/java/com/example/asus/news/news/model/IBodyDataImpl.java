package com.example.asus.news.news.model;

import android.util.Log;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.util.NetUtil;
import com.example.asus.news.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2016/10/31.
 */

public class IBodyDataImpl implements IBodyData {
    private String normal1="http://c.3g.163.com/nc/article/list/%s/%s.html";
  //  private String pic1="http://c.m.163.com/recommend/getChanListNews?channel=%s&size=20&offset=0&fn=2&passport=&devId=a194%2Fc7OpOnAZinR3Hxpvw%3D%3D&lat=LA0XCRYu8V5EPbOyATyZjA%3D%3D&lon=CGdWTj7l84OD%2Fw2lTAaT1g%3D%3D&version=16.0&net=wifi&ts=1476466127&sign=bFijK2tVnCz4wg%2BLq3QavP%2BtxfchsxjZiLDUzwy9nA948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=vivo_store2014_news&mac=4N4MskCKgPqYtR4r5mUWMLXXhQnPCVyraDgInk6Dmbk%3D";
     private  String pic1="http://c.m.163.com/recommend/getChanListNews?%s&size=10&offset=0&fn=2";
     private  String  photoUrl="http://c.m.163.com/photo/api/list/0096/54GI0096.json";
     private String bd="http://c.m.163.com/dlist/article/local/dynamic?from=5bm%2F5bee&offset=0&size=10&fn=2&passport=&devId=IvywnJ%2BQQSjPGVxCMeAhww%3D%3D&lat=xnNWsiAMz7ALu%2Fc2OGwbFg%3D%3D&lon=EB8zDm0aWBUS1YRVdSwwQA%3D%3D&version=17.1&net=wifi&ts=1478336409&sign=XZPuvG4tqFh108WIx53zHOCVdBhQod8HjH5tL%2Fewsg548ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=vAO9EPgi0MjYbFe%2FJ5lR5chl6XBe73qotuvJso3%2B3s0%3D ";
     private String wyhrd="http://c.m.163.com/recommend/getSubDocPic?from=netease_h&size=10&offset=0&fn=1&passport=&devId=IvywnJ%2BQQSjPGVxCMeAhww%3D%3D&lat=svqpHZh52FEtB1wBROMmZA%3D%3D&lon=24%2Bk1%2FugAeNW99yXvVf3hw%3D%3D&version=17.1&net=wifi&ts=1478274341&sign=epywTG%2BUTELvQTQYt5pwEMdJgGGjtaPb4maPTzINReh48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=vAO9EPgi0MjYbFe%2FJ5lR5chl6XBe73qotuvJso3%2B3s0%3D";
    private  String rd="http://c.m.163.com/recommend/getSubDocPic?size=10&offset=0&fn=2&passport=&devId=IvywnJ%2BQQSjPGVxCMeAhww%3D%3D&lat=q1pTaTSN0%2FNndua3lqkgMw%3D%3D&lon=oTzFiTU4Xfh5AFfYJ4Jyhg%3D%3D&version=17.1&net=wifi&ts=1478274578&sign=KyavL%2FIs8GN9o3VVS7qTEUlzS8LQk2hKsqGdJ5MX3Tt48ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=vAO9EPgi0MjYbFe%2FJ5lR5chl6XBe73qotuvJso3%2B3s0%3D";
    @Override
    public void getBodyData(TListBean tListBean, final OnDataLoadListener onDataLoadListener) {
          String tid = tListBean.getTid();
        String template = tListBean.getTemplate();
        String tname=null;
        String alias = tListBean.getAlias();
        String topicid = tListBean.getTopicid();
        int page = tListBean.getHasAD();
        Log.d("zanZQ", "getBodyData:page "+page);
        OkHttpClient client = NetUtil.getClient();
        final String url ;
        if (tListBean.getTname().equals("网易号")){
          url=wyhrd;
            tid="推荐";
        }else if (tListBean.getTname().equals("热点")){
          url=rd;
            tid="推荐";
        }else
        if (tListBean.getTname().equals("本地")){
            url=bd;
            tid="广州";
        }else if (tListBean.getTname().equals("图片")){
             url=photoUrl;
       }else if (topicid.contains("00341DA")||topicid.contains("0096SPCL")){
            url=String.format(pic1,"channel="+tid);
           tname = tListBean.getTname();
        }else{
           url = String.format(normal1, tid, page+"-20");
        }
         Request request=new Request.Builder()
                .url(url).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        Log.d("zanZQ", "getBodyData: "+url);
        final String finalTname = tname;
        final String finalTid = tid;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if (response.isSuccessful()){
                  TypeObject typeObject = new TypeObject();
                  String string = response.body().string();
                  Log.d("zanZQ", "onRespons13123131e: "+ finalTid +"..."+string);
                  if (finalTname !=null){
                      typeObject.setPicBeanList(ParseJsonUtil.getListByJsonPic(string,finalTname));
                      typeObject.setType(0);
                  }else if (url==photoUrl){
                      typeObject.setBodyBeanList(ParseJsonUtil.getListByJson(string, null));
                      typeObject.setType(1);
                  }else if (url==bd){
                      typeObject.setBodyBeanList(ParseJsonUtil.getListByJson(string, finalTid));
                      typeObject.setType(1);
                  }
                  else{
                      typeObject.setBodyBeanList(ParseJsonUtil.getListByJson(string, finalTid));
                      typeObject.setType(1);
                  }

                  onDataLoadListener.onLoadSuccess(typeObject);
              }else{
                  onDataLoadListener.onLoadSuccess(null);
              }
            }
        });
    }
}
