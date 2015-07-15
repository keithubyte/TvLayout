package com.vsoon.tvlayout;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by keith on 15/7/4.
 */
public class LayoutRadio {

    public static int STANDARD_WIDTH = 1280;
    public static int STANDARD_HEIGHT = 720;

    public static float RADIO_WIDTH;
    public static float RADIO_HEIGHT;
    public static float RADIO_AVERAGE;

    static {
        initRadio();
    }

    private static void initRadio() {
        RADIO_WIDTH = calculateRadio(STANDARD_WIDTH, true);
        RADIO_HEIGHT = calculateRadio(STANDARD_HEIGHT, false);
        RADIO_AVERAGE = (RADIO_WIDTH + RADIO_HEIGHT) / 2;
    }

    public static void resetStandard(int widht, int height) {
        if (widht > 0 && height > 0) {
            STANDARD_WIDTH = widht;
            STANDARD_HEIGHT = height;
            initRadio();
        }
    }

    private static float calculateRadio(int value, boolean isWidth) {
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        return isWidth ? dm.widthPixels / (float) value : dm.heightPixels / (float) value;
    }
}
