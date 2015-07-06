package com.vsoon.tvlayout;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

/**
 * Created by keith on 15/7/4.
 */
public class LayoutResizer {

    private static final float wRadio = LayoutRadio.RADIO_WIDTH;
    private static final float hRadio = LayoutRadio.RADIO_HEIGHT;

    private LayoutResizer(){};

    /**
     * Resize the size-relative attributions
     * @param child
     * @param params
     */
    public static void resize(View child, ViewGroup.LayoutParams params) {
        Log.e("LayoutResizer", "resize");
        if (child != null && params != null) {
            Log.e("LayoutResizer", "wRadio = " + wRadio + ", hRadio = " + hRadio);
            // width and height
            params.width = params.width > 0 ? (int) (params.width * wRadio) : params.width;
            params.height = params.height > 0 ? (int) (params.height * hRadio) : params.height;

            // margin
            if (params instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) params;
                p.leftMargin = (int) (p.leftMargin * wRadio);
                p.topMargin = (int) (p.topMargin * hRadio);
                p.rightMargin = (int) (p.rightMargin * wRadio);
                p.bottomMargin = (int) (p.bottomMargin * hRadio);
                p.setMarginStart((int) (p.getMarginStart() * hRadio));
                p.setMarginEnd((int) (p.getMarginEnd() * hRadio));
            }

            if (params instanceof AbsoluteLayout.LayoutParams) {
                AbsoluteLayout.LayoutParams p = (AbsoluteLayout.LayoutParams) params;
                p.x = (int) (p.x * wRadio);
                p.y = (int) (p.y * hRadio);
            }

            // padding
            int paddingLeft = (int) (child.getPaddingLeft() * wRadio);
            int paddingTop = (int) (child.getPaddingTop() * hRadio);
            int paddingRight = (int) (child.getPaddingRight() * wRadio);
            int paddingBottom = (int) (child.getPaddingBottom() * hRadio);
            child.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

            // font size and others
            if (child instanceof TextView) {
                TextView tv = (TextView) child;
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv.getTextSize() * hRadio);
                tv.setCompoundDrawablePadding((int) (tv.getCompoundDrawablePadding() * hRadio));
                tv.setMaxWidth((int) (tv.getMaxWidth() * wRadio));
                tv.setMaxHeight((int) (tv.getMaxHeight() * hRadio));
                tv.setMinWidth((int) (tv.getMinWidth() * wRadio));
                tv.setMinHeight((int) (tv.getMinHeight() * hRadio));
            }
        }
    }

}
