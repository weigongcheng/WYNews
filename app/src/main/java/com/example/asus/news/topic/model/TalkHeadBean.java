package com.example.asus.news.topic.model;

import java.util.List;

/**
 * Created by ASUS on 2016/11/4.
 */

public class TalkHeadBean {

    /**
     * focusNum : 277
     * picUrl : http://dingyue.nosdn.127.net/WLe7ov4WA3WYsPj7tQvtxYx9D3reEg99v4rVHc=ttXXAS1462440793735.jpg
     * topicId : SJ06081089410519724025
     * topicName : 红米
     */

    private List<话题Bean> 话题;

    public List<话题Bean> get话题() {
        return 话题;
    }

    public void set话题(List<话题Bean> 话题) {
        this.话题 = 话题;
    }

    public static class 话题Bean {
        private int focusNum;
        private String picUrl;
        private String topicId;
        private String topicName;

        public int getFocusNum() {
            return focusNum;
        }

        public void setFocusNum(int focusNum) {
            this.focusNum = focusNum;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }
    }
}
