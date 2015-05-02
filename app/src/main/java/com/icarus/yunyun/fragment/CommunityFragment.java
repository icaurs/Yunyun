package com.icarus.yunyun.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.icarus.yunyun.MainActivity;
import com.icarus.yunyun.R;
import com.icarus.yunyun.adapter.CommunityRvAdapter;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Community;
import com.icarus.yunyun.listerner.RecyclerScrollListener;
import com.icarus.yunyun.listerner.impl.ListScrollListenerImpl;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/4/9.
 */
public class CommunityFragment extends BaseFragment {

    private RecyclerView rv;

    private LinearLayoutManager layoutManager;

    private List<Community> communities;

    private CommunityRvAdapter communityRvAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, null);

        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        wheel = (ProgressWheel) rootView.findViewById(R.id.progress_wheel);
        swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        communities = new ArrayList<Community>();
        isRefreshFootIng = false;
        limit = 10;
        skip = 0;
        page = 1;
        listSize = 0;

        wheel.setVisibility(View.GONE);

        initRv();

        isRefreshFootIng = true;
        wheel.setVisibility(View.VISIBLE);
        getCommunity();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.menu_community, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.community == 1) {
            if (isRefreshFootIng == false) {
                isRefreshFootIng = true;
                isFoot = false;
                wheel.setVisibility(View.VISIBLE);
                limit = 10;
                skip = 0;
                page = 1;
                communities.clear();
                getCommunity();
            } else {
                toast("正在加载");
            }
            MainActivity.community = 0;
        }
    }

    /**
     * 初始化列表
     */
    public void initRv() {
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        communityRvAdapter = new CommunityRvAdapter(getActivity(), communities, bitmapUtils, httpUtils);
        rv.setAdapter(communityRvAdapter);
        communityRvAdapter.setOnItemClickListener(new CommunityRvAdapter.RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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
                limit = 10;
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
                    List<Article> articles = new ArrayList<Article>();
                    for (int j = 0; j < 3; j++) {
                        Article article = new Article();
                        article.setName("发布人昵称");
                        article.setLevel("等级1");
                        article.setDetail("文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容");
                        articles.add(article);
                        community.setArticles(articles);
                    }
                    communities.add(community);
                }
//                Article article = new Article();
//                article.setView(0);
//                articles.add(article);
                communityRvAdapter.notifyDataSetChanged();
                isRefreshFootIng = false;
                wheel.setVisibility(View.GONE);
                swipeLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
