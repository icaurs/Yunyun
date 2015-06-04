package com.icarus.yunyun;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icarus.yunyun.application.App;
import com.icarus.yunyun.util.SystemBarTintManager;
import com.icarus.yunyun.util.Utils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

/**
 * Created by DELL on 2015/4/9.
 */
public class BaseActivity extends AppCompatActivity {

    public App app;

    public Toolbar toolbar;

    public Utils utils;

    public BitmapUtils bitmapUtils;

    public HttpUtils httpUtils;

    public Gson gson;

    public int widthDisplay, heightDisplay;

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App) getApplication();
        utils = new Utils();
        bitmapUtils = new BitmapUtils(this);
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

    /**
     * 初始化toolbar
     */
    public void initToolBar(String title) {
        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.google_color_red);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }

    public void initToolBar(String title, String subTitle) {
        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.google_color_red);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setSubtitle(subTitle);
        setSupportActionBar(toolbar);
    }

    /**
     * 返回
     *
     * @author user
     */
    class BackNavClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            finish();
        }

    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
