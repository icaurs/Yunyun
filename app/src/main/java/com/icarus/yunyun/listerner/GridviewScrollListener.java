package com.icarus.yunyun.listerner;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

import com.icarus.yunyun.listerner.impl.ListScrollListenerImpl;

/**
 * Created by user on 2015-01-24.
 */
public class GridviewScrollListener implements AbsListView.OnScrollListener {
    private GridView gridView;

    /**
     * 判断listview中当前第几个item
     */
    private int lastItem;

    /**
     * 判断是否可更新
     */
    private boolean isRefreshFoot = false;

    /**
     * 判断是否正在更新
     */
    private boolean isRefreshFootIng;

    private int v = 0;

    private ListScrollListenerImpl listScrollListener;

    public GridviewScrollListener(GridView gridView, ListScrollListenerImpl listScrollListener, boolean isRefreshFootIng) {
        this.gridView = gridView;
        this.listScrollListener = listScrollListener;
        this.isRefreshFootIng = isRefreshFootIng;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
        try {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isRefreshFoot) {
                if (isRefreshFootIng == false) {
                    listScrollListener.loadView();
                } else {
                    listScrollListener.unLoadView();
                }
            }
        } catch (Exception e) {
            Log.w("ListviewScrollListener", e.toString());
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        try {
            lastItem = firstVisibleItem + visibleItemCount;
            // 判断 最后一条开始显示，那么加载新数据
            if (lastItem == totalItemCount) {
                isRefreshFoot = true;
            } else {
                isRefreshFoot = false;
            }
            v = visibleItemCount;
        } catch (Exception e) {
            Log.w("ListviewScrollListener", e.toString());
        }
    }
}
