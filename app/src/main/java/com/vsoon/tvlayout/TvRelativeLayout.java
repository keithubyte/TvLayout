package com.vsoon.tvlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by keith on 15/7/4.
 */
public class TvRelativeLayout extends RelativeLayout {

    public TvRelativeLayout(Context context) {
        super(context);
    }

    public TvRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }
}
