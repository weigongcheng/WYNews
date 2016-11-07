package com.example.asus.news.live.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2016/11/3.
 */
@Entity
public  class FutureBean {
    @Property
    private String startTime;
    @Property
    private String image;
    @Property
    private int roomId;
    @Property
    private String roomName;

    @Generated(hash = 484419440)
    public FutureBean(String startTime, String image, int roomId, String roomName) {
        this.startTime = startTime;
        this.image = image;
        this.roomId = roomId;
        this.roomName = roomName;
    }

    @Generated(hash = 1524233532)
    public FutureBean() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}