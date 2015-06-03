package com.icarus.yunyun;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icarus.yunyun.adapter.DrawerListAdapter;
import com.icarus.yunyun.entity.DrawerList;
import com.icarus.yunyun.fragment.ArticleFragment;
import com.icarus.yunyun.fragment.CommunityFragment;
import com.icarus.yunyun.fragment.TestFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by DELL on 2015/4/9.
 */
public class MainActivity extends BaseActivity {

    public static final String[] TITLES = {"测试菜单1", "测试菜单2", "测试菜单3"};

    public static final int[] ICONS = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private DrawerLayout mDrawer_layout;

    private RelativeLayout menu_layout_left;

    private ListView lvMenuLeft;

    private DrawerListAdapter drawerListAdapter;

    private List<DrawerList> drawerLists;

    public static int p;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    private Fragment fragment;

    private ActionBarDrawerToggle drawerToggle;

    /**
     * drawerStatus=0表示关闭，1表示打开
     */
    private int drawerStatus;

    private long exitTime;

    private MenuItem menuUser, menuLogin, menuSearch;

    private FrameLayout llUser, flMenuUser;

    private ImageView iv, ivMenuUser;

    private TextView tvMenuUser;

    private View headView;

    public static int community = 0;

    /**
     * 从您的应用服务器请求并获取的 Token。
     */
    private String Token = "Ni/jIbpy44BAB6vM7T85Qpg1odh66S9xFvHLIPtH8o0/UZpddtFVXHvstwdltcc9V2K7pkI+ZbkAPMuEfrBWnw==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar(getResources().getString(R.string.app_name));
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 右侧滑动菜单
        drawerLists = new ArrayList<DrawerList>();
        p = 0;

        mDrawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerStatus = 1;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                drawerStatus = 0;
            }
        };
        drawerToggle.syncState();
        mDrawer_layout.setDrawerListener(drawerToggle);
        menu_layout_left = (RelativeLayout) findViewById(R.id.menu_layout_left);
        lvMenuLeft = (ListView) menu_layout_left.findViewById(R.id.lvMenuLeft);
        headView = LayoutInflater.from(this).inflate(R.layout.list_head_user, null);
        ivMenuUser = (ImageView) headView.findViewById(R.id.ivMenuUser);
        tvMenuUser = (TextView) headView.findViewById(R.id.tvMenuUser);
        lvMenuLeft.addHeaderView(headView);

        drawerLists = getDrawerList();
        drawerListAdapter = new DrawerListAdapter(MainActivity.this, drawerLists);
        lvMenuLeft.setAdapter(drawerListAdapter);
//        lvMenuLeft.setSelector(new ColorDrawable(Color.TRANSPARENT));
        lvMenuLeft.setOnItemClickListener(new DrawerItemClickListenerRight());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initFragment(p);
            }
        }, 200);

        /**
         * IMKit SDK调用第二步，建立与服务器的连接。
         */
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                //Connect 成功
                toast(s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                //Connect 失败
                toast(errorCode.getMessage());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    class ToolbarMenuItemClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (drawerToggle.onOptionsItemSelected(menuItem)) {
                return true;
            }
            switch (menuItem.getItemId()){
                case R.id.comm:
                    intent = new Intent(MainActivity.this, CommunityActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        }
    }

    private class DrawerItemClickListenerRight implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            if (position == 0) {
//                if (app.user == null) {
//                    intent = new Intent(MainActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                } else {
//                    intent = new Intent(MainActivity.this, UserActivity.class);
//                    startActivity(intent);
//                }
                intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            } else if (p != position - 1) {
                p = position - 1;
                drawerListAdapter.notifyDataSetChanged();
                final Fragment f = fragments.get(position - 1);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switchContent(fragment, f);
                    }
                }, 200);
                mDrawer_layout.closeDrawer(menu_layout_left);// �ر�mMenu_layout
            }
        }
    }

    /**
     * 初始化第一个fragment
     */
    public void initFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragments = getFragments();
        fragment = fragments.get(position);
        // 加载fragment时刷新界面
        transaction.replace(R.id.flFragment, fragment);
        transaction.commit();
    }

    /**
     * 判断并加载fragment，如果未加载过则先执行hide，后执行add操作；如果加载过了先执行hide，后执行show操作
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (fragment != to) {
            fragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // 先判断是否被add过
            if (!to.isAdded()) {
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.hide(from).add(R.id.flFragment, to).commit();
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mDrawer_layout.isDrawerOpen(menu_layout_left)) {
                mDrawer_layout.closeDrawer(menu_layout_left);
            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public List<Fragment> getFragments() {
        fragments.add(new ArticleFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new TestFragment());
        return fragments;
    }

    public List<DrawerList> getDrawerList() {
        for (int i = 0; i < TITLES.length; i++) {
            DrawerList drawerList = new DrawerList();
            drawerList.setIcon(ICONS[i]);
            drawerList.setTitle(TITLES[i]);
            drawerLists.add(drawerList);
        }
        return drawerLists;
    }
}
