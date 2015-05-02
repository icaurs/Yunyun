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
import com.icarus.yunyun.entity.DrawerList;
import com.icarus.yunyun.view.CircleImageView;

import java.util.List;

/**
 * Created by user on 2015-01-12.
 */
public class DrawerListAdapter extends BaseAdapter {

    private Context context;

    private List<DrawerList> list;

    private LayoutInflater mInflater;

//    private int[] colors = {R.color.google_color_green, R.color.google_color_orange, R.color.google_color_blue, R.color.google_color_red};
    private int[] colors = {R.color.google_color_red, R.color.google_color_red, R.color.google_color_red, R.color.google_color_red};

    public DrawerListAdapter(Context context, List<DrawerList> list) {
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
            convertView = mInflater.inflate(R.layout.listview_drawer_item, null);
            holder = new ViewHolder();
            holder.ll = (LinearLayout) convertView.findViewById(R.id.ll);
            holder.iv = (CircleImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DrawerList drawerList = list.get(position);

        holder.iv.setImageResource(drawerList.getIcon());
        holder.iv.setBorderColor(context.getResources().getColor(colors[position % 4]));
        holder.tv.setText(drawerList.getTitle());

        if (MainActivity.p == position) {
            holder.tv.setTextColor(context.getResources().getColor(colors[position % 4]));
            holder.ll.setBackgroundResource(R.drawable.listview_item_press_bg);
        } else {
            holder.tv.setTextColor(context.getResources().getColor(R.color.dark));
            holder.ll.setBackgroundResource(R.drawable.listview_item_no_bg);
        }

        return convertView;
    }

    public class ViewHolder {
        private LinearLayout ll;
        private CircleImageView iv;
        private TextView tv;
    }
}
