package com.icarus.yunyun.application;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by DELL on 2015/4/9.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * IMKit SDK 初始化
         */
        RongIM.init(this);
    }
}
