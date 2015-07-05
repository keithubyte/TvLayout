package com.vsoon.tvlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by keith on 15/7/5.
 */
public class TvRadioGroup extends RadioGroup {

    public TvRadioGroup(Context context) {
        super(context);
    }

    public TvRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutResizer.resize(child, params); // resize the attributions before adding view
        super.addView(child, index, params);
    }

}
