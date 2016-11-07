package com.example.asus.news.news.presenter;

import android.os.Handler;

import com.example.asus.news.IShowUrlView;
import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.ISearchData;
import com.example.asus.news.news.model.ISearchDataImpl;

/**
 * Created by ASUS on 2016/11/1.
 */

public class WebViewPresenter {
    private IShowUrlView iShowUrlView;
    private ISearchData iSearchData;
    private Handler handler=new Handler();
    public WebViewPresenter(IShowUrlView iShowUrlView) {
        this.iShowUrlView = iShowUrlView;
        iSearchData=new ISearchDataImpl();
    }
    public  void  getUrl(String url, final int type, String id){
        iSearchData.getSearchResultData(url, id, type, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         iShowUrlView.getShowUrlWebView((String) object,type);
                     }
                 });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       iShowUrlView.getShowUrlWebView(null,0);
                   }
               });
            }
        });
    }
}
