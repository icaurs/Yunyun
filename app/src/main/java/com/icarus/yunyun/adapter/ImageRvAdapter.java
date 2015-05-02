package com.icarus.yunyun.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.icarus.yunyun.R;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Community;
import com.icarus.yunyun.entity.Image;
import com.icarus.yunyun.view.CircleImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.List;

/**
 * Created by DELL on 2015/4/13.
 */
public class ImageRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Image> list;

    private BitmapUtils bitmapUtils;

    private Intent intent;

    private LayoutInflater layoutInflater;;

    private RvItemClickListener rvItemClickListener;

    private RvItemLongClickListener rvItemLongClickListener;

    public ImageRvAdapter(Context context, List<Image> list, BitmapUtils bitmapUtils) {
        this.context = context;
        this.list = list;
        this.bitmapUtils = bitmapUtils;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case Community.TYPE_VIEW:
                view = layoutInflater.inflate(R.layout.recycler_image_item, parent, false);
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
        Image image = list.get(position);
        switch (getItemViewType(position)) {
            case Community.TYPE_VIEW:
                ViewHolderMain viewHolderMain = (ViewHolderMain) holder;
                viewHolderMain.iv.setImageResource(R.mipmap.ic_launcher);
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

        private ImageView iv;

        public ViewHolderMain(View view, RvItemClickListener rvItemClickListener, RvItemLongClickListener rvItemLongClickListener) {
            super(view);
            this.rvItemClickListener = rvItemClickListener;
            this.rvItemLongClickListener = rvItemLongClickListener;
            view.setOnClickListener(new ItemClick());
            view.setOnLongClickListener(new ItemLongClick());
            iv = (ImageView) view.findViewById(R.id.iv);
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
