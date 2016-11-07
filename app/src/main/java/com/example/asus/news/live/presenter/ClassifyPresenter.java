package com.example.asus.news.live.presenter;

import android.os.Handler;

import com.example.asus.news.live.model.FLHeadBean;
import com.example.asus.news.live.model.FLMainBean;
import com.example.asus.news.live.model.ILoadFLData;
import com.example.asus.news.live.model.ILoadFLDataImpl;
import com.example.asus.news.live.view.IShowHotDataView;
import com.example.asus.news.live.view.IShowLiveFlView;
import com.example.asus.news.news.OnDataLoadListener;

import java.util.List;

/**
 * Created by ASUS on 2016/11/2.
 */

public class ClassifyPresenter  {
    private ILoadFLData iLoadFLData;
    private IShowLiveFlView iShowLiveFlView;
    private IShowHotDataView iShowHotDataView;
    private Handler handler=new Handler();
    public ClassifyPresenter(IShowLiveFlView iShowLiveFlView) {
        this.iShowLiveFlView = iShowLiveFlView;
        iLoadFLData=new ILoadFLDataImpl();
    }

    public ClassifyPresenter(IShowHotDataView iShowHotDataView) {
        this.iShowHotDataView = iShowHotDataView;
        iLoadFLData=new ILoadFLDataImpl();
    }

    public void getHeadData(){
       iLoadFLData.getLoadFLHeadData(new OnDataLoadListener() {
           @Override
           public void onLoadSuccess(final Object object) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       iShowLiveFlView.getHeadView((List<FLHeadBean>) object);
                   }
               });
           }

           @Override
           public void onLoadFailed(String errorMsg) {
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                      iShowLiveFlView.getHeadView(null);
                       }
                   });
           }
       });
   };
    public void getMainData(String id,String page){
        iLoadFLData.getLoadFLMainData(id, page, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    iShowLiveFlView.getMainView((FLMainBean) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iShowLiveFlView.getMainView(null);
                }
            });
            }
        });
    }
    public void getHotMainData(String id){
        iLoadFLData.getLoadHotMainData(id, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     iShowHotDataView.getDataView(object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                  handler.post(new Runnable() {
                      @Override
                      public void run() {
                          iShowHotDataView.getDataView(null);
                      }
                  });
            }
        });
    }
}
