package com.example.asus.news.news.presenter;

import android.os.Handler;

import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.BodyBean;
import com.example.asus.news.news.model.IBodyData;
import com.example.asus.news.news.model.IBodyDataImpl;
import com.example.asus.news.news.model.TListBean;
import com.example.asus.news.news.model.TypeObject;
import com.example.asus.news.news.view.IShowBlankView;

import java.util.List;

/**
 * Created by ASUS on 2016/10/31.
 */

public class BodyPresenter {
    private IBodyData iBodyData;
    private IShowBlankView iShowBlankView;
    private Handler handler=new Handler();
    public BodyPresenter(IShowBlankView iShowBlankView) {
        this.iShowBlankView = iShowBlankView;
        iBodyData=new IBodyDataImpl();
    }
    public void start(TListBean listBean){
        iBodyData.getBodyData(listBean, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TypeObject typeObject = (TypeObject) object;
                        switch (typeObject.getType()){
                            case 0:
                                iShowBlankView.ShowBlankPicView(typeObject.getPicBeanList());
                            break;
                            case 1:
                                iShowBlankView.ShowBlankView(typeObject.getBodyBeanList());
                            break;
                        }
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iShowBlankView.ShowBlankView(null);
                    }
                });
            }
        });

    }
}
