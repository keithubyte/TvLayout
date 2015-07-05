package com.vsoon.tvlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by keith on 15/7/5.
 */
public class TvFrameLayout extends FrameLayout {

    public TvFrameLayout(Context context) {
        super(context);
    }

    public TvFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }
}
