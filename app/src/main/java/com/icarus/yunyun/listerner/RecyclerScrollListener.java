package com.icarus.yunyun.listerner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;

import com.icarus.yunyun.listerner.impl.ListScrollListenerImpl;

/**
 * Created by user on 2015-01-13.
 */
public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView rv;

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

    private ListScrollListenerImpl listScrollListenerImpl;

    private LinearLayoutManager layoutManager;

    public RecyclerScrollListener(RecyclerView rv, ListScrollListenerImpl listScrollListenerImpl, boolean isRefreshFootIng, LinearLayoutManager layoutManager) {
        this.rv = rv;
        this.listScrollListenerImpl = listScrollListenerImpl;
        this.isRefreshFootIng = isRefreshFootIng;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        try {
            if ((newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) && isRefreshFoot) {
                if (isRefreshFootIng == false) {
                    listScrollListenerImpl.loadView();
                } else {
                    listScrollListenerImpl.unLoadView();
                }
            }
        } catch (Exception e) {
            Log.w("RecyclerScrollListener", e.toString());
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

//        try {
//            lastItem = firstVisibleItem + visibleItemCount;
//            // 判断 最后一条开始显示，那么加载新数据
//            if (lastItem == totalItemCount) {
//                isRefreshFoot = true;
//            } else {
//                isRefreshFoot = false;
//            }
//            v = visibleItemCount;
//        }
//        catch (Exception e) {
//            Log.w("ListviewScrollListener", e.toString());
//        }

        int lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();
        //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载
        // dy>0 表示向下滑动
        if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
            isRefreshFoot = true;
        } else {
            isRefreshFoot = false;
        }
    }
}
