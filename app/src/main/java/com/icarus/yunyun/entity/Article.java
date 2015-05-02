package com.icarus.yunyun.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2015-04-21.
 */
public class Article extends BaseEntity implements Serializable {

    private String name;

    private String level;

    private String detail;

    private List<Image> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
