package com.example.asus.news.util;

import android.util.Log;

import com.example.asus.news.news.model.ADS;
import com.example.asus.news.news.model.BodyBean;
import com.example.asus.news.news.model.PicBean;
import com.example.asus.news.news.model.SearchBean;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public class ParseJsonUtil {
    public static String path="http://news.163.com/photoview/%s/%s";
    public static List<BodyBean> getListByJson( String json, String tid){
        List<BodyBean> list=new ArrayList<>();
        try {

            if (tid!=null){
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray(tid);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject data = jsonArray.getJSONObject(i);
                    BodyBean bodyBean = new BodyBean();
                    if ( data.has("ads")) {
                        JSONArray ads = data.getJSONArray("ads");
                        if (ads.length()>0) {
                            List<ADS> adSlist=new  ArrayList<>();
                            for (int j = 0; j < ads.length(); j++) {
                                JSONObject object = ads.getJSONObject(j);
                                String title = object.getString("title");
                                String imgsrc = object.getString("imgsrc");
                                String url = object.getString("url");
                                if (url.contains("|")){
                                    String substring = url.substring(0, url.lastIndexOf("|"));
                                    String substring1 = url.substring(url.lastIndexOf("|") + 1, url.length());
                                    url=String.format(path,substring,substring1)+".html";
                                }
                                ADS ads1 = new ADS(title,imgsrc,url);
                                adSlist.add(ads1);
                            }
                            bodyBean.setType(0);
                            bodyBean.setList(adSlist);
                        }

                    }else if (data.has("skipType")){
                        String skipType = data.getString("skipType");
                        if (skipType.equals("live")){
                            String tag = data.getString("TAG");
                            JSONObject live_info = data.getJSONObject("live_info");
                            String user_count = live_info.getString("user_count");
                            bodyBean.setTag(tag);
                            bodyBean.setUser_count(user_count);
                            String docid = data.getString("docid");
                            String skipID = data.getString("skipID");
                            bodyBean.setSkipID(skipID);
                            bodyBean.setDocid(docid);
                            bodyBean.setType(3);
                        }else if (skipType.equals("special")){
                            if (data.has("editor")){
                                bodyBean.setEditor(true);
                                String docid = data.getString("docid");
                                String skipID = data.getString("skipID");
                                bodyBean.setSkipID(skipID);
                                bodyBean.setDocid(docid);
                                bodyBean.setType(3);
                            }else{
                                String docid = data.getString("docid");
                                String skipID = data.getString("skipID");
                                bodyBean.setSkipID(skipID);
                                bodyBean.setDocid(docid);
                                bodyBean.setType(2);
                            }
                          if (data.has("url_3w")){
                              String url = data.getString("url_3w");
                              bodyBean.setUrl(url);
                          }
                        }else if (skipType.equals("photoset")){

                           if (data.has("imgextra")){
                                List<String> listimg=new ArrayList<>();
                                JSONArray imgextra = data.getJSONArray("imgextra");
                                for (int j = 0; j < imgextra.length(); j++) {
                                    String string = imgextra.getJSONObject(j).getString("imgsrc");
                                    listimg.add(string);
                                }
                               String url = data.getString("skipID");
                               if (url.contains("|")){
                                   String substring = url.substring(0, url.lastIndexOf("|"));
                                   String substring1 = url.substring(url.lastIndexOf("|") + 1, url.length());
                                   url=String.format(path,substring,substring1)+".html";
                               }
                               bodyBean.setUrl(url);
                               bodyBean.setImgextra(listimg);
                                bodyBean.setType(1);
                            }else{
                               List<ADS> adSlist=new  ArrayList<>();
                               String title = data.getString("title");
                               String imgsrc = data.getString("imgsrc");
                               String url = data.getString("skipID");
                               if (url.contains("|")){
                                   String substring = url.substring(0, url.lastIndexOf("|"));
                                   String substring1 = url.substring(url.lastIndexOf("|") + 1, url.length());
                                   url=String.format(path,substring,substring1)+".html";
                               }
                               ADS ads1 = new ADS(title,imgsrc,url);
                               adSlist.add(ads1);
                               bodyBean.setType(0);
                               bodyBean.setUrl(url);
                               bodyBean.setList(adSlist);
                           }

                        }else if (skipType.equals("video")){
                            String title = data.getString("title");
                            String source = data.getString("source");
                            String videoID = data.getString("videoID");
                            String tags = data.getString("TAGS");
                            String imgsrc = data.getString("imgsrc");
                          //  String tid1 = data.getJSONObject("videoTopic").getString("tid");
                          String tid1=  data.getString("skipID");
                           bodyBean.setTag(tags);
                            bodyBean.setSkipID(tid1);
                            bodyBean.setImgsrc(imgsrc);
                            bodyBean.setSource(source);
                            bodyBean.setTitle(title);
                            bodyBean.setVideoID(videoID);
                            bodyBean.setType(7);
                        }

                    }
                    else {
                      if (!tid.equals("推荐")){
                      if (data.has("hasHead")){
                          String url = data.getString("url_3w");
                          if (url.length()<6){
                              url = data.getString("postid");
                              if (url.contains("|")){
                                  String substring = url.substring(0, url.lastIndexOf("|"));
                                  String substring1 = url.substring(url.lastIndexOf("|") + 1, url.length());
                                  url=String.format(path,substring,substring1)+".html";
                              }
                          }

                          String digest = data.getString("digest");
                       //   bodyBean.setUrl(url);
                          bodyBean.setDigest(digest);
                          bodyBean.setType(0);
                          List<ADS> adSlist=new  ArrayList<>();
                          String title = data.getString("title");
                          String imgsrc = data.getString("imgsrc");
                          ADS ads1 = new ADS(title,imgsrc,url);
                          adSlist.add(ads1);
                          bodyBean.setType(0);
                          bodyBean.setList(adSlist);
                      }else{
                          String url;
                        if (tid.equals("T1444270454635")){
                            url=data.getString("url");
                        }else{
                           url = data.getString("url_3w");
                        }

                          if (!url.contains("http")){
                              url=data.getString("docid");
                          }
                          String digest = data.getString("digest");
                          bodyBean.setUrl(url);
                          bodyBean.setDigest(digest);
                          bodyBean.setType(2);
                      }
                      }else{
                          bodyBean.setType(2);
                      }


                    }

                    String title = data.getString("title");
                    if (!tid.equals("推荐")){
                        String source = data.getString("source");
                        bodyBean.setSource(source);
                        String ptime = data.getString("ptime");
                        bodyBean.setPtime(ptime);
                    }
                    String imgsrc = data.getString("imgsrc");
                    bodyBean.setTitle(title);
                    bodyBean.setImgsrc(imgsrc);
                    list.add(bodyBean);
                    Log.d("zanZQ", "getListByJson: "+tid+"p p "+i);
                }
            }else{
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject data = array.getJSONObject(i);
                    String setname = data.getString("setname");
                    String replynum = data.getString("replynum");
                    String imgsum = data.getString("imgsum");
                    String seturl = data.getString("seturl");
                    JSONArray pics = data.getJSONArray("pics");
                    List<String> stringList=new ArrayList<>();
                    for (int j = 0; j < pics.length(); j++) {
                        stringList.add(pics.getString(j));
                    }
                    //http://c.m.163.com/photo/api/set/0096/112545.json
                    //http://help.3g.163.com/photoview/54GI0096/112545.html
                    seturl=seturl.replace("help.3g","c.m").replace("photoview/54GI","photo/api/set/").replace("html","json");
                    BodyBean bodyBean = new BodyBean();
                    bodyBean.setTitle(setname);
                    bodyBean.setUser_count(replynum);
                    bodyBean.setUrl(seturl);
                    bodyBean.setImgextra(stringList);
                    bodyBean.setSkipID(imgsum);

                    switch (i%4){
                        case 0:
                        case 2:
                            bodyBean.setType(4);
                            break;
                        case 1:
                            bodyBean.setType(5);
                            break;
                        case 3:
                            bodyBean.setType(6);
                            break;
                    }

                    list.add(bodyBean);
                }
            }

             return list;
        } catch (JSONException e) {


        }
        return null;
    }
    public static List<SearchBean> getSearchBeanListByJson(String json){
        List<SearchBean> list=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray ask = jsonObject.getJSONArray("ask");
            if (ask.length()>0) {
                for (int i = 0; i < ask.length(); i++) {
                    JSONObject data = ask.getJSONObject(i);
                    String id = data.getString("id");
                    String title = data.getString("title");
                    String skipType = data.getString("skipType");
                    String ptime = data.getString("ptime");
                    SearchBean searchBean = new SearchBean();
                    searchBean.setTitle(title);
                    searchBean.setPtime(ptime);
                    searchBean.setId(id);
                    searchBean.setSkipType(skipType);
                    searchBean.setType(0);
                    list.add(searchBean);
                }
            }
            JSONArray topiclist = jsonObject.getJSONArray("topiclist");
            if (topiclist.length()>0) {
                for (int i = 0; i < topiclist.length(); i++) {
                    JSONObject data = topiclist.getJSONObject(i);
                    String id = data.getString("tid");
                    String tname = data.getString("tname");
                    SearchBean searchBean = new SearchBean();
                    searchBean.setType(1);
                    searchBean.setId(id);
                    searchBean.setTname(tname);
                    list.add(searchBean);
                }
            }
            JSONObject baike = jsonObject.getJSONObject("baike");
            String digest = baike.getString("digest");
            String title = baike.getString("title");
            String url = baike.getString("url");
            SearchBean searchBean = new SearchBean();
            searchBean.setDigest(digest);
            searchBean.setUrl(url);
            searchBean.setTitle(title);
            searchBean.setType(2);
            list.add(searchBean);
            JSONArray array = jsonObject.getJSONObject("doc").getJSONArray("result");
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);
                String docid = data.getString("skipID");
                String title1 = data.getString("title");
                String skipType = data.getString("skipType");
                String imgurl = data.getString("imgurl");
                String ptime = data.getString("ptime");
                SearchBean Bean = new SearchBean();
                Bean.setId(docid);
                Bean.setPtime(ptime);
                Bean.setTitle(title1);
                Bean.setSkipType(skipType);
                Bean.setImgurl(imgurl);
                Bean.setType(3);
                list.add(Bean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<PicBean> getListByJsonPic(String json,String finalTname) {
        List<PicBean> list=new ArrayList<>();
        try {

            JSONArray array = new JSONObject(json).getJSONArray(finalTname);
            for (int i = 0; i < array.length(); i++) {
                PicBean picBean = new PicBean();
                JSONObject data = array.getJSONObject(i);
                String title = data.getString("title");
                if (data.has("imgsrc")){
                    String imgsrc = data.getString("imgsrc");
                    picBean.setImgsrc(imgsrc);
                    String pixel = data.getString("pixel");
                    picBean.setPixel(pixel);
                }
                int replyCount = data.getInt("replyCount");
                int downTimes = data.getInt("downTimes");
                int upTimes = data.getInt("upTimes");
                String boardid = data.getString("boardid");
                String replyid = data.getString("replyid");

                picBean.setTitle(title);

             picBean.setReplyid(replyid);
                picBean.setReplyCount(replyCount);
                picBean.setUpTimes(upTimes);
                picBean.setDownTimes(downTimes);
                picBean.setBoardid(boardid);
                list.add(picBean);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
