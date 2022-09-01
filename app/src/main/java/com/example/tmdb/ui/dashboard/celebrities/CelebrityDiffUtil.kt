package com.example.tmdb.ui.dashboard.celebrities

import androidx.recyclerview.widget.DiffUtil
import com.example.tmdb.response.celebrities.Celebrity

class CelebrityDiffUtil: DiffUtil.ItemCallback<Celebrity>() {
    override fun areItemsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Celebrity, newItem: Celebrity): Boolean {
        return oldItem == newItem
    }
}