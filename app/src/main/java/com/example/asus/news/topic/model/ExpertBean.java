package com.example.asus.news.topic.model;

/**
 * Created by ASUS on 2016/11/3.
 */

public class ExpertBean {

    /**
     * id : EX08527977195924810258
     * name : 韦喜
     * headpicurl : http://dingyue.nosdn.127.net/FNK1Jw7ksITvLMwzC6MmRXXen6I7pzLmTS9CA4KI5f44b1477554508330.jpg
     * concernCount : 2722
     * type : 1
     * questionCount : 100
     */

    private String id;
    private String name;
    private String headpicurl;
    private int concernCount;
    private int type;
    private int questionCount;

    public ExpertBean(String id, String name, String headpicurl, int concernCount, int type, int questionCount) {
        this.id = id;
        this.name = name;
        this.headpicurl = headpicurl;
        this.concernCount = concernCount;
        this.type = type;
        this.questionCount = questionCount;
    }

    public ExpertBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadpicurl() {
        return headpicurl;
    }

    public void setHeadpicurl(String headpicurl) {
        this.headpicurl = headpicurl;
    }

    public int getConcernCount() {
        return concernCount;
    }

    public void setConcernCount(int concernCount) {
        this.concernCount = concernCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
