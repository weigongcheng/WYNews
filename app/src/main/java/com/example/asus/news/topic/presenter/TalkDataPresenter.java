package com.example.asus.news.topic.presenter;

import android.os.Handler;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.topic.model.ILoadTalkData;
import com.example.asus.news.topic.model.ILoadTalkDataImpl;
import com.example.asus.news.topic.model.TalkDataBean;
import com.example.asus.news.topic.model.TalkHeadBean;
import com.example.asus.news.topic.view.IShowTalkView;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TalkDataPresenter {
    private IShowTalkView iShowTalkView;
    private ILoadTalkData iLoadTalkData;
private Handler handler=new Handler();
    public TalkDataPresenter(IShowTalkView iShowTalkView) {
        this.iShowTalkView = iShowTalkView;
        iLoadTalkData=new ILoadTalkDataImpl();
    }
    public void getTalkMainData(String page){
        iLoadTalkData.getLoadTalkData(page, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowTalkView.getTalkData((List<TalkDataBean>) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
              handler.post(new Runnable() {
                  @Override
                  public void run() {
                      iShowTalkView.getTalkData(null);
                  }
              });
            }
        });
    }
    public void  getTalkHeadData(){
        iLoadTalkData.getLoadTalkHeadDara(new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowTalkView.getTalkHeadData((TalkHeadBean) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       iShowTalkView.getTalkHeadData(null);
                   }
               });
            }
        });
    }
}
