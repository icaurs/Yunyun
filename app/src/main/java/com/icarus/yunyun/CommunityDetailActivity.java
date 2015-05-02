package com.icarus.yunyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.icarus.yunyun.adapter.ArticleLvAdapter;
import com.icarus.yunyun.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-04-25.
 */
public class CommunityDetailActivity extends BaseActivity {

    private ScrollView sv;

    private ImageView ivBg, ivComm;

    private TextView tvName, tvNum, btnJoin, btnDetail, btnMember;

    private ListView lv;

    private ArticleLvAdapter articleLvAdapter;

    private List<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        articles = new ArrayList<Article>();

        initToolBar("社群详情");
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new BackNavClick());

        sv = (ScrollView) findViewById(R.id.sv);
        ivBg = (ImageView) findViewById(R.id.ivBg);
        ivComm = (ImageView) findViewById(R.id.ivComm);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNum = (TextView) findViewById(R.id.tvNum);
        btnJoin = (TextView) findViewById(R.id.btnJoin);
        btnDetail = (TextView) findViewById(R.id.btnDetail);
        btnMember = (TextView) findViewById(R.id.btnMember);
        lv = (ListView) findViewById(R.id.lv);

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(CommunityDetailActivity.this, CommunityDetailIntroActivity.class);
                startActivity(intent);
            }
        });

        initArticle();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    class ToolbarMenuItemClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){

            }
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        lv.setFocusable(false);
        sv.setFocusable(true);
        sv.setFocusableInTouchMode(true);
        sv.requestFocus();
    }

    public void initArticle(){
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setName("发布人昵称"+i);
            article.setLevel("等级1");
            article.setDetail("文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容");
            articles.add(article);
        }
        articleLvAdapter = new ArticleLvAdapter(CommunityDetailActivity.this, articles);
        lv.setAdapter(articleLvAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(CommunityDetailActivity.this, ArticleDetailActivity.class);
                intent.putExtra("article",articles.get(i));
                startActivity(intent);
            }
        });
    }
}
