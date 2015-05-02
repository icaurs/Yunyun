package com.icarus.yunyun.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.icarus.yunyun.R;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Community;
import com.icarus.yunyun.entity.Image;
import com.icarus.yunyun.view.CircleImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2015/4/13.
 */
public class ArticleRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Article> list;

    private BitmapUtils bitmapUtils;

    private HttpUtils httpUtils;

    private Intent intent;

    private LayoutInflater layoutInflater;
    ;

    private RvItemClickListener rvItemClickListener;

    private RvItemLongClickListener rvItemLongClickListener;

    public ArticleRvAdapter(Context context, List<Article> list, BitmapUtils bitmapUtils, HttpUtils httpUtils) {
        this.context = context;
        this.list = list;
        this.bitmapUtils = bitmapUtils;
        this.httpUtils = httpUtils;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case Community.TYPE_VIEW:
                view = layoutInflater.inflate(R.layout.recycler_article_item, parent, false);
                viewHolder = new ViewHolderMain(view, rvItemClickListener, rvItemLongClickListener);
                break;
            case Community.TYPE_FOOT:
                view = layoutInflater.inflate(R.layout.recycler_foot, parent, false);
                viewHolder = new ViewHolderFoot(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Article article = list.get(position);
        switch (getItemViewType(position)) {
            case Community.TYPE_VIEW:
                ViewHolderMain viewHolderMain = (ViewHolderMain) holder;
                viewHolderMain.tvName.setText(article.getName());
                viewHolderMain.tvLevel.setText(article.getLevel());
                viewHolderMain.tvArticle.setText(article.getDetail());
                List<Image> images = article.getImages();
                if (images != null && images.size() != 0) {
                    ImageRvAdapter imageRvAdapter = new ImageRvAdapter(context, images, bitmapUtils);
                    viewHolderMain.rv.setAdapter(imageRvAdapter);
                    imageRvAdapter.setOnItemClickListener(new ImageRvAdapter.RvItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                    viewHolderMain.rv.setVisibility(View.VISIBLE);
                    viewHolderMain.tvArticle.setMaxLines(3);
                } else {
                    viewHolderMain.rv.setVisibility(View.GONE);
                    viewHolderMain.tvArticle.setMaxLines(5);
                }
                break;
            case Community.TYPE_FOOT:
                ViewHolderFoot viewHolderFoot = (ViewHolderFoot) holder;

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getView();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(RvItemClickListener listener) {
        this.rvItemClickListener = listener;
    }

    public void setOnItemLongClickListener(RvItemLongClickListener listener) {
        this.rvItemLongClickListener = listener;
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder {

        private RvItemClickListener rvItemClickListener;

        private RvItemLongClickListener rvItemLongClickListener;

        private CardView cardview;

        private CircleImageView ivArticle;

        private TextView tvName, tvLevel, tvTime, tvArticle;

        private RecyclerView rv;

        private LinearLayoutManager layoutManager;

        public ViewHolderMain(View view, RvItemClickListener rvItemClickListener, RvItemLongClickListener rvItemLongClickListener) {
            super(view);
            this.rvItemClickListener = rvItemClickListener;
            this.rvItemLongClickListener = rvItemLongClickListener;
            view.setOnClickListener(new ItemClick());
            view.setOnLongClickListener(new ItemLongClick());
            cardview = (CardView) view.findViewById(R.id.cardview);
            ivArticle = (CircleImageView) view.findViewById(R.id.ivArticle);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvLevel = (TextView) view.findViewById(R.id.tvLevel);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvArticle = (TextView) view.findViewById(R.id.tvArticle);
            rv = (RecyclerView) view.findViewById(R.id.rv);
            rv.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(layoutManager);
            rv.setItemAnimator(new DefaultItemAnimator());
        }

        class ItemClick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (rvItemClickListener != null) {
                    rvItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        }

        class ItemLongClick implements View.OnLongClickListener {

            @Override
            public boolean onLongClick(View v) {
                if (rvItemLongClickListener != null) {
                    rvItemLongClickListener.onItemLongClick(v, getLayoutPosition());
                }
                return true;
            }
        }

    }

    public class ViewHolderFoot extends RecyclerView.ViewHolder {

        public ViewHolderFoot(View view) {
            super(view);

        }

    }

    public interface RvItemClickListener {
        public void onItemClick(View view, int position);
    }

    public interface RvItemLongClickListener {
        public void onItemLongClick(View view, int position);
    }
}
