package com.icarus.yunyun.entity;

/**
 * Created by DELL on 2015/4/30.
 */
public class BaseEntity {

    public static final int TYPE_FOOT = 0; //view类型0

    public static final int TYPE_VIEW = 1; //view类型1

    private int view;

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
