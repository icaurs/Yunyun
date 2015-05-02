package com.icarus.yunyun.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by DELL on 2015/4/29.
 */
public class NestingRecyclerView extends RecyclerView {

    public NestingRecyclerView(Context context) {
        super(context);
    }

    public NestingRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestingRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
