package com.icarus.yunyun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icarus.yunyun.application.App;
import com.icarus.yunyun.util.Utils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

/**
 * Created by DELL on 2015/4/9.
 */
public class BaseFragment extends Fragment {

    public App app;

    public Toolbar toolbar;

    public Utils utils;

    public BitmapUtils bitmapUtils;

    public HttpUtils httpUtils;

    public Gson gson;

    public int widthDisplay, heightDisplay;

    public Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        app = (App) getActivity().getApplication();
        utils = new Utils();
        bitmapUtils = new BitmapUtils(getActivity());
        httpUtils = new HttpUtils();
        gson = new Gson();

        getDisplay();
    }

    /**
     * 获取屏幕分辨率
     *
     * @return
     */
    public void getDisplay() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        widthDisplay = dm.widthPixels;
        heightDisplay = dm.heightPixels;
    }

    public void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
