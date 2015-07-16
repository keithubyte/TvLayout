package com.vsoon.tvlayout;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;

/**
 * Created by keith on 15/7/4.
 */
public class LayoutRadio {

    private static final String TAG = "LayoutRadio";

    public static float STANDARD_WIDTH = 1280.0f;
    public static float STANDARD_HEIGHT = 720.0f;

    public static float RADIO_WIDTH = 1.0f;
    public static float RADIO_HEIGHT = 1.0f;
    public static float RADIO_AVERAGE = 1.0f;

    public static void initRadio(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        RADIO_WIDTH = point.x / STANDARD_WIDTH;
        RADIO_HEIGHT = point.y / STANDARD_HEIGHT;
        RADIO_AVERAGE = (RADIO_WIDTH + RADIO_HEIGHT) / 2;
        Log.e(TAG, "RADIO_WIDTH = " + RADIO_WIDTH + ", RADIO_HEIGHT = " + RADIO_HEIGHT + ", RADIO_AVERAGE = " + RADIO_AVERAGE);
    }

    public static void initRadio(float wRadio, float hRadio, float aRadio) {
        RADIO_WIDTH = wRadio;
        RADIO_HEIGHT = hRadio;
        RADIO_AVERAGE = aRadio;
    }

}
