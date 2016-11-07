package com.example.asus.news.topic.model;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public class TalkDataBean {
    private List<ExpertBean> expertList;
      private String subjectId;
    private String name;
    private String classification;
    private String concernCount;
    private String talkCount;
    private List<UserTalkBean> userTalkBeen;
    private List<String> talkPicture;
    private int type;

    public TalkDataBean() {
    }

    public List<ExpertBean> getExpertList() {
        return expertList;
    }

    public void setExpertList(List<ExpertBean> expertList) {
        this.expertList = expertList;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getConcernCount() {
        return concernCount;
    }

    public void setConcernCount(String concernCount) {
        this.concernCount = concernCount;
    }

    public String getTalkCount() {
        return talkCount;
    }

    public void setTalkCount(String talkCount) {
        this.talkCount = talkCount;
    }

    public List<UserTalkBean> getUserTalkBeen() {
        return userTalkBeen;
    }

    public void setUserTalkBeen(List<UserTalkBean> userTalkBeen) {
        this.userTalkBeen = userTalkBeen;
    }

    public List<String> getTalkPicture() {
        return talkPicture;
    }

    public void setTalkPicture(List<String> talkPicture) {
        this.talkPicture = talkPicture;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TalkDataBean(List<ExpertBean> expertList, String subjectId, String name, String classification, String concernCount, String talkCount, List<UserTalkBean> userTalkBeen, List<String> talkPicture, int type) {

        this.expertList = expertList;
        this.subjectId = subjectId;
        this.name = name;
        this.classification = classification;
        this.concernCount = concernCount;
        this.talkCount = talkCount;
        this.userTalkBeen = userTalkBeen;
        this.talkPicture = talkPicture;
        this.type = type;
    }
}
