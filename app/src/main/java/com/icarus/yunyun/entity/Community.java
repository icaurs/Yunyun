package com.icarus.yunyun.entity;

import java.util.List;

/**
 * Created by DELL on 2015/4/13.
 */
public class Community extends BaseEntity {

    private String name;

    private List<Article> articles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
