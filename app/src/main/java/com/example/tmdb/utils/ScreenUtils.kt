package com.example.tmdb.utils

import android.content.Context

object ScreenUtils {
    fun convertDpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}