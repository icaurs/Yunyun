package com.icarus.yunyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icarus.yunyun.R;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.Comment;
import com.icarus.yunyun.view.CircleImageView;

import java.util.List;

/**
 * Created by user on 2015-01-12.
 */
public class CommentLvAdapter extends BaseAdapter {

    private Context context;

    private List<Comment> list;

    private LayoutInflater mInflater;

    public CommentLvAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_comment_item, null);
            holder = new ViewHolder();
            holder.ivUser = (ImageView) convertView.findViewById(R.id.ivUser);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Comment comment = list.get(position);

        holder.tvName.setText(comment.getName());
        holder.tvTime.setText(comment.getTime());
        holder.tvDetail.setText(comment.getDetail());

        return convertView;
    }

    public class ViewHolder {
        private ImageView ivUser;
        private TextView tvName, tvTime, tvDetail;
    }
}
