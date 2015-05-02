package com.icarus.yunyun.entity;

import java.io.Serializable;

/**
 * Created by DELL on 2015/4/29.
 */
public class Image extends BaseEntity implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
