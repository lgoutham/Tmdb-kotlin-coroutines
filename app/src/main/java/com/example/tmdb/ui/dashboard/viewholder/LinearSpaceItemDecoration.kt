package com.example.tmdb.ui.dashboard.viewholder

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.tmdb.utils.ScreenUtils

class LinearSpaceItemDecoration(private val context: Context, private val spaceSize: Int) :
    ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
            outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.6).toFloat())
        } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.right = ScreenUtils.convertDpToPx(context, spaceSize.toFloat())
        } else {
            outRect.right = ScreenUtils.convertDpToPx(context, (spaceSize * 0.6).toFloat())
        }
    }
}