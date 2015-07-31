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

    public static int STANDARD_WIDTH = 1280;
    public static int STANDARD_HEIGHT = 720;

    public static int REAL_WIDTH = 0;
    public static int REAL_HEIGHT = 0;

    public static float RADIO_WIDTH = 1.0f;
    public static float RADIO_HEIGHT = 1.0f;
    public static float RADIO_AVERAGE = 1.0f;

    public static void initRadio(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        REAL_WIDTH = point.x;
        REAL_HEIGHT = point.y;
        RADIO_WIDTH = REAL_WIDTH / (float) STANDARD_WIDTH;
        RADIO_HEIGHT = REAL_HEIGHT / (float) STANDARD_HEIGHT;
        RADIO_AVERAGE = (RADIO_WIDTH + RADIO_HEIGHT) / 2;
        Log.e(TAG, "REAL_WIDTH = " + REAL_WIDTH
                + ", REAL_HEIGHT = " + REAL_HEIGHT
                + ", RADIO_WIDTH = " + RADIO_WIDTH
                + ", RADIO_HEIGHT = " + RADIO_HEIGHT
                + ", RADIO_AVERAGE = " + RADIO_AVERAGE);
    }

    public static void initRadio(float wRadio, float hRadio, float aRadio) {
        RADIO_WIDTH = wRadio;
        RADIO_HEIGHT = hRadio;
        RADIO_AVERAGE = aRadio;
    }

}
