package com.vsoon.tvlayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by keith on 15/7/5.
 */
public class TvViewPager extends ViewPager {

    public TvViewPager(Context context) {
        super(context);
    }

    public TvViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }

}
