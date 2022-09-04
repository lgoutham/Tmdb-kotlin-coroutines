package com.example.tmdb.utils;

import android.content.Context;

public class ScreenUtils {

    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
