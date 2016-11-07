package com.example.asus.news.live.presenter;

import android.os.Handler;

import com.example.asus.news.live.IShowLiveDetailsView;
import com.example.asus.news.live.model.IDLiveData;
import com.example.asus.news.live.model.IDLiveDataImpl;
import com.example.asus.news.live.model.LiveDetailsBean;
import com.example.asus.news.news.OnDataLoadListener;

/**
 * Created by ASUS on 2016/11/2.
 */

public class LiveDetailsPresenter {
    private IShowLiveDetailsView iShowLiveDetailsView;
    private IDLiveData idLiveData;
   private Handler handler=new Handler();
    public LiveDetailsPresenter(IShowLiveDetailsView iShowLiveDetailsView) {
        this.iShowLiveDetailsView = iShowLiveDetailsView;
        idLiveData=new IDLiveDataImpl();
    }
    public void getLiveDetailsData(String id){
        idLiveData.getLiveDetailsData(id, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowLiveDetailsView.getLiveDetailsView((LiveDetailsBean) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         iShowLiveDetailsView.getLiveDetailsView(null);
                     }
                 });
            }
        });
    }
}
