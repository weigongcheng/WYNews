package com.example.asus.news.live.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2016/11/2.
 */
@Entity
public class FLHeadBean {

    /**
     * order : 3
     * id : 3
     * type : column
     * name : TOP100
     * visible : true
     */
    @Property
    private int order;
    @Property
    private int id;
    @Property
    private String type;
    @Property
    private String name;
    @Property
    private boolean visible;

    @Generated(hash = 2126806765)
    public FLHeadBean(int order, int id, String type, String name,
            boolean visible) {
        this.order = order;
        this.id = id;
        this.type = type;
        this.name = name;
        this.visible = visible;
    }



    @Generated(hash = 1772950516)
    public FLHeadBean() {
    }

    public int getOrder() {
        return order;
    }



    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }



    public boolean getVisible() {
        return this.visible;
    }
}
