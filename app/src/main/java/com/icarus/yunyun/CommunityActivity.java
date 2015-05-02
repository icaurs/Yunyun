package com.icarus.yunyun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.icarus.yunyun.adapter.CommunityListRvAdapter;
import com.icarus.yunyun.adapter.CommunityRvAdapter;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Community;
import com.icarus.yunyun.listerner.RecyclerScrollListener;
import com.icarus.yunyun.listerner.impl.ListScrollListenerImpl;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-04-24.
 */
public class CommunityActivity extends BaseActivity {

    private MenuItem menuSearch;

    private SearchView searchView;

    private String commName;

    private RecyclerView rv;

    private LinearLayoutManager layoutManager;

    private List<Community> communities;

    private CommunityListRvAdapter communityListRvAdapter;

    private RecyclerScrollListener recyclerScrollListener;

    /**
     * 判断是否正在更新
     */
    private boolean isRefreshFootIng;

    /**
     * listview是否滚动到最底部没有数据加载
     */
    private boolean isFoot = false;

    /**
     * limit表示每一次加载数据量 skip表示从第几个开始加载 page表示第几页
     */
    private int limit, skip, page;

    private int listSize;

    private ProgressWheel wheel;

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        initToolBar("社群");
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new BackNavClick());

        communities = new ArrayList<Community>();
        isRefreshFootIng = false;
        limit = 20;
        skip = 0;
        page = 1;
        listSize = 0;

        rv = (RecyclerView) findViewById(R.id.rv);
        wheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        wheel.setVisibility(View.GONE);

        initRv();

        isRefreshFootIng = true;
        wheel.setVisibility(View.VISIBLE);
        getCommunity();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_community_list, menu);

        menuSearch = menu.findItem(R.id.search);
        searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchTextListener());
        changeSearchViewTextColor(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    class SearchTextListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String s) {
            commName = s;
            if (isRefreshFootIng == false) {
                isRefreshFootIng = true;
                isFoot = false;
                wheel.setVisibility(View.VISIBLE);
                limit = 20;
                skip = 0;
                page = 1;
                communities.clear();
                getCommunity();
            } else {
                toast("正在加载");
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            commName = s;
            return false;
        }
    }

    public void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    class ToolbarMenuItemClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){

            }
            return true;
        }
    }

    /**
     * 初始化列表
     */
    public void initRv() {
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        communityListRvAdapter = new CommunityListRvAdapter(this, communities, bitmapUtils, httpUtils);
        rv.setAdapter(communityListRvAdapter);
        communityListRvAdapter.setOnItemClickListener(new CommunityListRvAdapter.RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(CommunityActivity.this, CommunityDetailActivity.class);
                startActivity(intent);
            }
        });

        recyclerScrollListener = new RecyclerScrollListener(rv, listScrollListenerImpl, isRefreshFootIng, layoutManager);
        rv.setOnScrollListener(recyclerScrollListener);

        swipeLayout.setOnRefreshListener(new SwipeRefresh());
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

    }

    ListScrollListenerImpl listScrollListenerImpl = new ListScrollListenerImpl() {
        @Override
        public void loadView() {
            if (isRefreshFootIng == false && isFoot == false) {
                isRefreshFootIng = true;
                wheel.setVisibility(View.VISIBLE);
                getCommunity();
            }
        }

        @Override
        public void unLoadView() {

        }
    };

    class SwipeRefresh implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            // TODO Auto-generated method stub
            if (isRefreshFootIng == false) {
                isRefreshFootIng = true;
                isFoot = false;
//                pb.setVisibility(View.VISIBLE);
                limit = 20;
                skip = 0;
                page = 1;
                communities.clear();
                getCommunity();
            } else {
                swipeLayout.setRefreshing(false);
            }
        }

    }

    /**
     * 获取数据
     */
    public void getCommunity() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Community community = new Community();
                    community.setName("社群名称" + i);
                    community.setView(1);
                    communities.add(community);
                }
//                Article article = new Article();
//                article.setView(0);
//                articles.add(article);
                communityListRvAdapter.notifyDataSetChanged();
                isRefreshFootIng = false;
                wheel.setVisibility(View.GONE);
                swipeLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
