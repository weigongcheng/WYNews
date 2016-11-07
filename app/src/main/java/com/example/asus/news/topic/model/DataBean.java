package com.example.asus.news.topic.model;

import java.util.List;

/**
 * Created by ASUS on 2016/11/3.
 */

public  class DataBean {
    /**
     * expertId : EX08527977195924810258
     * alias : 我是律师韦喜，关于促销季来临遭遇价格欺诈应该如何维权的相关问题，问我吧！
     * stitle :
     * picurl : http://dingyue.nosdn.127.net/TwdNz5PdhuhX6sEGhzaEhZbfBfOx61ONaWvvusheCJG8e1478131821274.jpg
     * name : 韦喜
     * description : 我是韦喜，为安徽某律师事务所专职律师，自执业以来，办理过大量的民商事案件，包括公司业务，房产纠纷，交通事故等类型。
     （特别提示：专家的回复仅代表其个人意见，不应被作为任何决策的依据，尤其是在法律、医疗等专业领域）

     * headpicurl : http://dingyue.nosdn.127.net/FNK1Jw7ksITvLMwzC6MmRXXen6I7pzLmTS9CA4KI5f44b1477554508330.jpg
     * classification : 法律
     * state : 1
     * expertState : 1
     * concernCount : 1850
     * createTime : 1477971243562
     * title : 律师
     * questionCount : 86
     * answerCount : 67
     */

    private List<DataBean.ExpertListBean> expertList;
    private List<?> localExpert;

    public List<DataBean.ExpertListBean> getExpertList() {
        return expertList;
    }

    public void setExpertList(List<DataBean.ExpertListBean> expertList) {
        this.expertList = expertList;
    }

    public List<?> getLocalExpert() {
        return localExpert;
    }

    public void setLocalExpert(List<?> localExpert) {
        this.localExpert = localExpert;
    }

    public static class ExpertListBean {
        private String expertId;
        private String alias;
        private String stitle;
        private String picurl;
        private String name;
        private String description;
        private String headpicurl;
        private String classification;
        private int state;
        private int expertState;
        private int concernCount;
        private long createTime;
        private String title;
        private int questionCount;
        private int answerCount;

        public String getExpertId() {
            return expertId;
        }

        public void setExpertId(String expertId) {
            this.expertId = expertId;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getStitle() {
            return stitle;
        }

        public void setStitle(String stitle) {
            this.stitle = stitle;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHeadpicurl() {
            return headpicurl;
        }

        public void setHeadpicurl(String headpicurl) {
            this.headpicurl = headpicurl;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getExpertState() {
            return expertState;
        }

        public void setExpertState(int expertState) {
            this.expertState = expertState;
        }

        public int getConcernCount() {
            return concernCount;
        }

        public void setConcernCount(int concernCount) {
            this.concernCount = concernCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getQuestionCount() {
            return questionCount;
        }

        public void setQuestionCount(int questionCount) {
            this.questionCount = questionCount;
        }

        public int getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(int answerCount) {
            this.answerCount = answerCount;
        }
    }
}
