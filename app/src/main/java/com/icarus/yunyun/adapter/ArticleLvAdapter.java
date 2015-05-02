package com.icarus.yunyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icarus.yunyun.MainActivity;
import com.icarus.yunyun.R;
import com.icarus.yunyun.entity.Article;
import com.icarus.yunyun.entity.DrawerList;
import com.icarus.yunyun.view.CircleImageView;

import java.util.List;

/**
 * Created by user on 2015-01-12.
 */
public class ArticleLvAdapter extends BaseAdapter {

    private Context context;

    private List<Article> list;

    private LayoutInflater mInflater;

    public ArticleLvAdapter(Context context, List<Article> list) {
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
            convertView = mInflater.inflate(R.layout.listview_article_item, null);
            holder = new ViewHolder();
            holder.ll = (LinearLayout) convertView.findViewById(R.id.ll);
            holder.ivArticle = (CircleImageView) convertView.findViewById(R.id.ivArticle);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvLevel = (TextView) convertView.findViewById(R.id.tvLevel);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
            holder.tvArticle = (TextView) convertView.findViewById(R.id.tvArticle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Article article = list.get(position);

        holder.tvName.setText(article.getName());
        holder.tvLevel.setText(article.getLevel());
        holder.tvArticle.setText(article.getDetail());

        return convertView;
    }

    public class ViewHolder {
        private LinearLayout ll;
        private CircleImageView ivArticle;
        private TextView tvName, tvLevel, tvTime, tvArticle;
    }
}
