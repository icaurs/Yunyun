package com.icarus.yunyun.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.icarus.yunyun.ObsBaseActivity;
import com.icarus.yunyun.R;
import com.icarus.yunyun.adapter.SimpleHeaderRecyclerAdapter;
import com.icarus.yunyun.adapter.SimpleRecyclerAdapter;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import java.util.ArrayList;

/**
 * Created by user on 2015-05-02.
 */
public class ObsBaseFragment extends Fragment {

    public BitmapUtils bitmapUtils;

    public HttpUtils httpUtils;

    public Intent intent;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bitmapUtils = new BitmapUtils(getActivity());
        httpUtils = new HttpUtils();
    }

    public static ArrayList<String> getDummyData() {
        return ObsBaseActivity.getDummyData();
    }

    protected int getActionBarSize() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = activity.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight() {
        Activity activity = getActivity();
        if (activity == null) {
            return 0;
        }
        return activity.findViewById(android.R.id.content).getHeight();
    }

    protected void setDummyData(ListView listView) {
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getDummyData()));
    }

    protected void setDummyDataWithHeader(ListView listView, View headerView) {
        listView.addHeaderView(headerView);
        setDummyData(listView);
    }

    protected void setDummyData(GridView gridView) {
        gridView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getDummyData()));
    }

    protected void setDummyData(RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleRecyclerAdapter(getActivity(), getDummyData()));
    }

    protected void setDummyDataWithHeader(RecyclerView recyclerView, View headerView) {
        recyclerView.setAdapter(new SimpleHeaderRecyclerAdapter(getActivity(), getDummyData(), headerView));
    }

    public void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
