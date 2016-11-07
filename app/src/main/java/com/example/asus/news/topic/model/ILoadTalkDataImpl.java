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

public class ILoadTalkDataImpl implements ILoadTalkData {
    private String talkMainUrl="http://topic.comment.163.com/topic/list/subject/%s-10.html";
   private String talkHeadUrl="http://c.3g.163.com/recommend/getChanRecomNews?channel=T1460094487214&size=5&passport=&devId=IvywnJ%2BQQSjPGVxCMeAhww%3D%3D&lat=Xd%2FLPs%2BZqqcjyhbvgTqwWw%3D%3D&lon=wchcT6rZQ8eZTla2sPbXJw%3D%3D&version=16.0&net=wifi&ts=1478170236&sign=ZYJAotXihYhWJ3qEuN09jmVGxMVWq%2FFEbCT%2FKxEiYW148ErR02zJ6%2FKXOnxX046I&encryption=1&canal=miliao_news&mac=vAO9EPgi0MjYbFe%2FJ5lR5chl6XBe73qotuvJso3%2B3s0%3Dl";

    @Override
    public void getLoadTalkData(String id, final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder().url(String.format(talkMainUrl,id)).addHeader("Accept-Encoding","UTF-8").addHeader("User-Agent","NewsApp/17.0 Android/5.0.2 (Xiaomi/Redmi Note 2)").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String string = response.body().string();
                    int position=-1;
                    List<TalkDataBean> list=new ArrayList<TalkDataBean>();
                    try {
                        JSONObject data = new JSONObject(string).getJSONObject("data");
                        if (data.has("recomendExpert")){
                            position = data.getJSONObject("recomendExpert").getInt("position")-1;
                        }

                        JSONArray subjectList = data.getJSONArray("subjectList");
                        for (int i = 0; i < subjectList.length(); i++) {
                            Log.d("zanZQ", "onResponse: "+i);
                            if (i==position){
                                List<ExpertBean> expertBeanList=new ArrayList<ExpertBean>();
                                JSONArray array = data.getJSONObject("recomendExpert").getJSONArray("expertList");
                                for (int j = 0; j < array.length(); j++) {
                                    JSONObject expert = array.getJSONObject(j);
                                    String id2 = expert.getString("id");
                                    String name = expert.getString("name");
                                    String headpicurl = expert.getString("headpicurl");
                                    int concernCount = expert.getInt("concernCount");
                                    int type = expert.getInt("type");
                                    int questionCount = expert.getInt("questionCount");
                                    expertBeanList.add(new ExpertBean(id2,name,headpicurl,concernCount,type,questionCount));
                                }
                                TalkDataBean talkDataBean = new TalkDataBean();
                                talkDataBean.setExpertList(expertBeanList);
                                talkDataBean.setType(0);
                                list.add(talkDataBean);
                            }
                            JSONObject object = subjectList.getJSONObject(i);
                            TalkDataBean bean = new TalkDataBean();
                            if (object.has("talkPicture")){
                               JSONArray talkPicture = object.getJSONArray("talkPicture");
                               List<String> stringList=new ArrayList<String>();
                               for (int j = 0; j < talkPicture.length(); j++) {
                                   String talkPictureString = talkPicture.getString(j);
                                   stringList.add(talkPictureString);
                               }
                              bean.setType(2);
                                bean.setTalkPicture(stringList);
                           }else{
                                JSONArray talkContent = object.getJSONArray("talkContent");
                                List<UserTalkBean> userTalkBeanList=new ArrayList<UserTalkBean>();
                                for (int j = 0; j < talkContent.length(); j++) {
                                    JSONObject jsonObject = talkContent.getJSONObject(j);
                                    String talkId = jsonObject.getString("talkId");
                                    String content = jsonObject.getString("content");
                                    String userHeadPicUrl = jsonObject.getString("userHeadPicUrl");
                                    userTalkBeanList.add(new UserTalkBean(talkId,content,userHeadPicUrl));
                                }
                                bean.setType(1);
                                bean.setUserTalkBeen(userTalkBeanList);
                            }
                            String classification = object.getString("classification");
                            String subjectId = object.getString("subjectId");
                            String name = object.getString("name");
                            String concernCount = object.getString("concernCount");
                            String talkCount = object.getString("talkCount");
                            bean.setClassification(classification);
                            bean.setName(name);
                            bean.setConcernCount(concernCount);
                            bean.setTalkCount(talkCount);
                            bean.setSubjectId(subjectId);
                            list.add(bean);
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
    public void getLoadTalkHeadDara(final OnDataLoadListener onDataLoadListener) {
        OkHttpClient client = NetUtil.getClient();
        Request request=new Request.Builder().url(talkHeadUrl).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onDataLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  if (response.isSuccessful()){
                      String string = response.body().string();
                      TalkHeadBean talkHeadBean = new Gson().fromJson(string, TalkHeadBean.class);
                      onDataLoadListener.onLoadSuccess(talkHeadBean);
                  }else{
                      onDataLoadListener.onLoadSuccess(null);
                  }
            }
        });
    }
}
