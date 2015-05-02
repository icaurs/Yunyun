package com.icarus.yunyun.entity;

import java.io.Serializable;

/**
 * Created by DELL on 2015/4/29.
 */
public class Comment extends BaseEntity implements Serializable {

    private String name;

    private String time;

    private String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
