package com.icarus.yunyun.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.icarus.yunyun.ArticleDetailActivity;
import com.icarus.yunyun.R;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Community;
import com.icarus.yunyun.view.CircleImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.List;

/**
 * Created by DELL on 2015/4/13.
 */
public class CommunityRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Community> list;

    private BitmapUtils bitmapUtils;

    private HttpUtils httpUtils;

    private Intent intent;

    private LayoutInflater layoutInflater;;

    private RvItemClickListener rvItemClickListener;

    private RvItemLongClickListener rvItemLongClickListener;

    public CommunityRvAdapter(Context context, List<Community> list, BitmapUtils bitmapUtils, HttpUtils httpUtils) {
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
                view = layoutInflater.inflate(R.layout.recycler_community_item, parent, false);
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
        Community community = list.get(position);
        switch (getItemViewType(position)) {
            case Community.TYPE_VIEW:
                ViewHolderMain viewHolderMain = (ViewHolderMain) holder;
                viewHolderMain.tvCommunityName.setText(community.getName());
                viewHolderMain.btnJoin.setBackgroundResource(R.drawable.button_red_bg);
                final List<Article> articles = community.getArticles();
                ArticleLvAdapter articleLvAdapter = new ArticleLvAdapter(context, articles);
                viewHolderMain.lv.setAdapter(articleLvAdapter);
                viewHolderMain.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        intent = new Intent(context, ArticleDetailActivity.class);
                        intent.putExtra("article",articles.get(i));
                        context.startActivity(intent);
                    }
                });
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

        private CircleImageView ivCommunity;

        private TextView tvCommunityName, tvCommunityDetail;

        private ListView lv;

        private Button btnJoin;

        public ViewHolderMain(View view, RvItemClickListener rvItemClickListener, RvItemLongClickListener rvItemLongClickListener) {
            super(view);
            this.rvItemClickListener = rvItemClickListener;
            this.rvItemLongClickListener = rvItemLongClickListener;
            view.setOnClickListener(new ItemClick());
            view.setOnLongClickListener(new ItemLongClick());
            cardview = (CardView) view.findViewById(R.id.cardview);
            ivCommunity = (CircleImageView) view.findViewById(R.id.ivCommunity);
            tvCommunityName = (TextView) view.findViewById(R.id.tvCommunityName);
            tvCommunityDetail = (TextView) view.findViewById(R.id.tvCommunityDetail);
            lv = (ListView) view.findViewById(R.id.lv);
            btnJoin = (Button) view.findViewById(R.id.btnJoin);
        }

        class ItemClick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (rvItemClickListener != null) {
                    rvItemClickListener.onItemClick(v, getPosition());
                }
            }
        }

        class ItemLongClick implements View.OnLongClickListener {

            @Override
            public boolean onLongClick(View v) {
                if (rvItemLongClickListener != null) {
                    rvItemLongClickListener.onItemLongClick(v, getPosition());
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
