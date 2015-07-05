package com.vsoon.tvlayout;

import android.content.res.Resources;

/**
 * Created by keith on 15/7/4.
 */
public class LayoutRadio {

    public static float STANDARD_WIDTH = 1920.0f;
    public static float STANDARD_HEIGHT = 1080.0f;

    public static float RADIO_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels / STANDARD_WIDTH;
    public static float RADIO_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels / STANDARD_HEIGHT;

}
