package com.example.asus.news.live.model;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TimeFutureBean {
    private  String day;
    private List<FutureBean> list;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<FutureBean> getList() {
        return list;
    }

    public void setList(List<FutureBean> list) {
        this.list = list;
    }

    public TimeFutureBean(String day, List<FutureBean> list) {
        this.day = day;
        this.list = list;
    }

    public TimeFutureBean() {
    }
}
