package com.example.asus.news.news.presenter;

import android.os.Handler;

import com.example.asus.news.IShowSearchView;
import com.example.asus.news.news.OnDataLoadListener;
import com.example.asus.news.news.model.ISearchData;
import com.example.asus.news.news.model.ISearchDataImpl;
import com.example.asus.news.news.model.SearchBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/1.
 */

public class SearchPresenter {
    private IShowSearchView iShowSearchView;
    private ISearchData iSearchData;
  private Handler handler=new Handler();
    public SearchPresenter(IShowSearchView iShowSearchView) {
        this.iShowSearchView = iShowSearchView;
        iSearchData=new ISearchDataImpl();
    }
    public void SearchKeywords (String keywords){
        iSearchData.getSearchData(keywords, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final Object object) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                       iShowSearchView.ShowSearchView((List<SearchBean>) object);
                    }
                });
            }

            @Override
            public void onLoadFailed(String errorMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                iShowSearchView.ShowSearchView(null);
            }
        });
            }
        });
    }
}
