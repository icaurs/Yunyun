package com.icarus.yunyun;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.icarus.yunyun.adapter.CommentLvAdapter;
import com.icarus.yunyun.adapter.ImageRvAdapter;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Comment;
import com.icarus.yunyun.entity.Image;
import com.icarus.yunyun.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-04-28.
 */
public class ArticleDetailActivity extends BaseActivity {

    private MenuItem menuComment;

    private ScrollView sv;

    private Article article;

    private CircleImageView ivArticle;

    private TextView tvName, tvLevel, tvTime, tvArticle;

    private RecyclerView rv;

    private LinearLayoutManager layoutManager;

    private List<Image> images;

    private ImageRvAdapter imageRvAdapter;

    private ListView lv;

    private CommentLvAdapter commentLvAdapter;

    private List<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        if (savedInstanceState == null) {
            article = (Article) getIntent().getSerializableExtra("article");
        } else {
            article = (Article) savedInstanceState.getSerializable("article");
        }
        images = new ArrayList<Image>();
        comments = new ArrayList<Comment>();

        initToolBar("");
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new BackNavClick());

        sv = (ScrollView) findViewById(R.id.sv);
        ivArticle = (CircleImageView) findViewById(R.id.ivArticle);
        tvName = (TextView) findViewById(R.id.tvName);
        tvLevel = (TextView) findViewById(R.id.tvLevel);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvArticle = (TextView) findViewById(R.id.tvArticle);
        rv = (RecyclerView) findViewById(R.id.rv);
        lv = (ListView) findViewById(R.id.lv);

        getArtile();

        initRv();

        getComments();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("article", article);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_article_detail, menu);

        menuComment = menu.findItem(R.id.comment);
        menuComment.setTitle("评论（99）");

        return super.onCreateOptionsMenu(menu);
    }

    class ToolbarMenuItemClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.comment:
                    toast("comment");
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        rv.setFocusable(false);
        lv.setFocusable(false);
        sv.setFocusable(true);
        sv.setFocusableInTouchMode(true);
        sv.requestFocus();
    }

    public void initRv() {
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        List<Image> imageList = article.getImages();
        if (null != imageList) {
            for (int i = 0; i < imageList.size(); i++) {
                Image image = imageList.get(i);
                image.setView(1);
                images.add(image);
            }
        }
        if (images != null && images.size() != 0) {
            imageRvAdapter = new ImageRvAdapter(this, images, bitmapUtils);
            rv.setAdapter(imageRvAdapter);
            imageRvAdapter.setOnItemClickListener(new ImageRvAdapter.RvItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
            rv.setVisibility(View.VISIBLE);
        } else {
            rv.setVisibility(View.GONE);
        }
    }

    public void getArtile() {
        tvName.setText(article.getName());
        tvLevel.setText(article.getLevel());
        tvArticle.setText(article.getDetail());
    }

    public void getComments() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setName("测试数据测试数据" + i);
            comment.setTime("2014-12-12 12:12");
            comment.setDetail("评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容" +
                    "评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容" + i);
            comment.setView(1);
            comments.add(comment);
        }
        if (comments.size() > 0) {
            commentLvAdapter = new CommentLvAdapter(ArticleDetailActivity.this, comments);
            lv.setAdapter(commentLvAdapter);
        }
    }

}
