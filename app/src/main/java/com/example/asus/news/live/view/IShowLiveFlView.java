package com.example.asus.news.live.view;

import com.example.asus.news.live.model.FLHeadBean;
import com.example.asus.news.live.model.FLMainBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/2.
 */

public interface IShowLiveFlView {
    void getHeadView(List<FLHeadBean> list);
    void getMainView(FLMainBean flMainBean);
}
