package com.vsoon.tvlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by keith on 15/7/5.
 */
public class TvGridView extends GridView {

    public TvGridView(Context context) {
        super(context);
    }

    public TvGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TvGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }

}
