package com.example.tmdb.ui.dashboard.viewholder

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.utils.ScreenUtils

class GridItemSpaceDecoration(
    private val context: Context,
    private val spaceSize: Int,
    private val spanCount: Int,
    private val orientation: Int = RecyclerView.HORIZONTAL
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (orientation == RecyclerView.HORIZONTAL) {
            if (position < spanCount) {
                outRect.left = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
                outRect.bottom = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
                outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.75).toFloat())
            } else if (position % spanCount == 0) {
                outRect.bottom = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
                outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.75).toFloat())
            } else if (position > parent.childCount - spanCount + 1) {
                outRect.right = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
            } else if (position % spanCount == 1) {
                outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.75).toFloat())
            }
        } else {
            if (position % spanCount == 0) {
                outRect.left = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
                outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.35).toFloat())
                outRect.bottom = ScreenUtils.convertDpToPx(context, (spaceSize * 0.5).toFloat())
            } else if (position > parent.childCount - spanCount + 1) {
                outRect.bottom = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
            } else if (position % spanCount == 1) {
                outRect.left = ScreenUtils.convertDpToPx(context, (spaceSize * 0.35).toFloat())
                outRect.right = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
                outRect.bottom = ScreenUtils.convertDpToPx(context, (spaceSize * 0.35).toFloat())
            }
        }
    }
}