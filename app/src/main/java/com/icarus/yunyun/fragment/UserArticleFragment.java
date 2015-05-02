package com.icarus.yunyun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.icarus.yunyun.ArticleDetailActivity;
import com.icarus.yunyun.MainActivity;
import com.icarus.yunyun.R;
import com.icarus.yunyun.adapter.ArticleRvAdapter;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Image;
import com.icarus.yunyun.listerner.RecyclerScrollListener;
import com.icarus.yunyun.listerner.impl.ListScrollListenerImpl;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-05-02.
 */
public class UserArticleFragment extends ObsBaseFragment {

    private ObservableRecyclerView rv;

    private LinearLayoutManager layoutManager;

    private List<Article> articles;

    private ArticleRvAdapter articleRvAdapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_article, container, false);

        rv = (ObservableRecyclerView) rootView.findViewById(R.id.scroll);
        wheel = (ProgressWheel) rootView.findViewById(R.id.progress_wheel);

//        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv.setHasFixedSize(false);
//        setDummyData(rv);
//        rv.setTouchInterceptionViewGroup((ViewGroup) getActivity().findViewById(R.id.container));
//
//        if (getActivity() instanceof ObservableScrollViewCallbacks) {
//            rv.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
//        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        articles = new ArrayList<Article>();
        isRefreshFootIng = false;
        limit = 10;
        skip = 0;
        page = 1;
        listSize = 0;

        wheel.setVisibility(View.GONE);

        initRv();

        isRefreshFootIng = true;
        wheel.setVisibility(View.VISIBLE);
        getArticle();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.community == 1) {
            if (isRefreshFootIng == false) {
                isRefreshFootIng = true;
                isFoot = false;
                wheel.setVisibility(View.VISIBLE);
                limit = 3;
                skip = 0;
                page = 1;
                articles.clear();
                getArticle();
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
        articleRvAdapter = new ArticleRvAdapter(getActivity(), articles, bitmapUtils, httpUtils);
        rv.setAdapter(articleRvAdapter);
        articleRvAdapter.setOnItemClickListener(new ArticleRvAdapter.RvItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(getActivity(), ArticleDetailActivity.class);
                intent.putExtra("article", articles.get(position));
                startActivity(intent);
            }
        });

        recyclerScrollListener = new RecyclerScrollListener(rv, listScrollListenerImpl, isRefreshFootIng, layoutManager);
        rv.setOnScrollListener(recyclerScrollListener);

        rv.setTouchInterceptionViewGroup((ViewGroup) getActivity().findViewById(R.id.container));

        if (getActivity() instanceof ObservableScrollViewCallbacks) {
            rv.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
        }
    }

    ListScrollListenerImpl listScrollListenerImpl = new ListScrollListenerImpl() {
        @Override
        public void loadView() {
            if (isRefreshFootIng == false && isFoot == false) {
                isRefreshFootIng = true;
                wheel.setVisibility(View.VISIBLE);
                getArticle();
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
                articles.clear();
                getArticle();
            }
        }

    }

    /**
     * 获取数据
     */
    public void getArticle() {
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 20; j++) {
                    Article article = new Article();
                    article.setName("发布人昵称");
                    article.setLevel("等级1");
                    article.setDetail("文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容" +
                            "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容" +
                            "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容" +
                            "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容");
                    article.setView(1);
                    List<Image> images = new ArrayList<Image>();
                    for (int i = 0; i < 10; i++) {
                        Image image = new Image();
                        image.setName("test" + i);
                        image.setView(1);
                        images.add(image);
                    }
                    article.setImages(images);
                    articles.add(article);
                }
                articleRvAdapter.notifyDataSetChanged();
                isRefreshFootIng = false;
                wheel.setVisibility(View.GONE);
            }
        }, 1000);
    }
}
