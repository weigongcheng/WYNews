package com.example.asus.news.topic.model;

/**
 * Created by ASUS on 2016/11/3.
 */

public class UserTalkBean {

    /**
     * talkId : 159617445198299136
     * content : 不错
     * userHeadPicUrl : http://imgm.ph.126.net/7wa0pY4kec3rySn616t7bw==/6631312956797821064.jpg
     */

    private String talkId;
    private String content;
    private String userHeadPicUrl;

    public UserTalkBean(String talkId, String content, String userHeadPicUrl) {
        this.talkId = talkId;
        this.content = content;
        this.userHeadPicUrl = userHeadPicUrl;
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserHeadPicUrl() {
        return userHeadPicUrl;
    }

    public void setUserHeadPicUrl(String userHeadPicUrl) {
        this.userHeadPicUrl = userHeadPicUrl;
    }
}
