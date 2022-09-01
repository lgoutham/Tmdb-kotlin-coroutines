package com.example.tmdb.ui.dashboard.search

import androidx.recyclerview.widget.DiffUtil
import com.example.tmdb.response.search.Result

class SearchDiffUtil : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}