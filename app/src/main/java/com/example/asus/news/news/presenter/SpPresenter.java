package com.example.asus.news.news.presenter;

import android.os.Handler;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.ILiveData;
import com.example.asus.news.news.model.ILiveDataImpl;
import com.example.asus.news.news.model.SpBean;
import com.example.asus.news.news.view.IShowSpDataView;

/**
 * Created by ASUS on 2016/11/5.
 */

public class SpPresenter {
    private ILiveData liveData;
    private IShowSpDataView iShowSpDataView;
  private Handler handler=new Handler();
    public SpPresenter(IShowSpDataView iShowSpDataView) {
        this.iShowSpDataView = iShowSpDataView;
        liveData=new ILiveDataImpl();
    }
    public void getData(){
        liveData.getLiveData(new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                   iShowSpDataView.getShowSpDataView((SpBean) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         iShowSpDataView.getShowSpDataView(null);
                     }
                 });
            }
        });
    }
}
