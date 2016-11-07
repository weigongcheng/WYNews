package com.example.asus.news.topic.presenter;

import android.os.Handler;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.topic.model.ILoadTopData;
import com.example.asus.news.topic.model.ILoadTopDataImpl;
import com.example.asus.news.topic.model.WbMainBean;
import com.example.asus.news.topic.model.WbTopBean;
import com.example.asus.news.topic.view.IShowWbTopView;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class WbTopDataPresenter {
    private IShowWbTopView iShowWbTopView;
    private ILoadTopData iLoadTopData;
    private Handler handler=new Handler();
    public WbTopDataPresenter(IShowWbTopView iShowWbTopView) {
        this.iShowWbTopView = iShowWbTopView;
        iLoadTopData=new ILoadTopDataImpl();
    }
    public void getTopData(){
        iLoadTopData.getLoadTopData(new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       iShowWbTopView.getTopDataView((List<WbTopBean>) object);
                   }
               });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
          handler.post(new Runnable() {
              @Override
              public void run() {
                  iShowWbTopView.getTopDataView(null);
              }
          });
            }
        });
    }
    public void getWbMainData(String type,String page){
        iLoadTopData.getLoadWbMainData(type, page, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowWbTopView.getMainDataView((WbMainBean) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
              handler.post(new Runnable() {
                  @Override
                  public void run() {
                      iShowWbTopView.getMainDataView(null);
                  }
              });
            }
        });
    }
}
