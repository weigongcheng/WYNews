package com.example.asus.news.news.presenter;

import android.os.Handler;

import com.example.asus.news.ITitleView;
import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.ITitleData;
import com.example.asus.news.news.model.ITitleDataImpl;
import com.example.asus.news.news.model.TitleBean;


/**
 * Created by ASUS on 2016/10/30.
 */

public class TitlePresenter {
    private ITitleData iTitleData;
     private ITitleView iTitleView;

    public TitlePresenter(ITitleView iTitleView) {
        this.iTitleView = iTitleView;
        iTitleData=new ITitleDataImpl();
    }
    public void start(){
        iTitleData.getTitleData(new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(Object object) {
                iTitleView.obtainTitleData((TitleBean) object);
            }

            @Override
            public void onLoadFailed(String errorMsg) {
                 iTitleView.obtainTitleData(null);
            }
        });
    }
}
