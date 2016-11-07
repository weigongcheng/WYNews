package com.example.asus.news.topic.view;

import com.example.asus.news.topic.model.TalkDataBean;
import com.example.asus.news.topic.model.TalkHeadBean;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public interface IShowTalkView {
    void  getTalkData(List<TalkDataBean> talkDataBeanList);
    void  getTalkHeadData(TalkHeadBean talkHeadBean);

}
