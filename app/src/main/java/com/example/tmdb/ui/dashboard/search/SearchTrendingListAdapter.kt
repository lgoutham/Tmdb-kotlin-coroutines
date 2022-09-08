package com.example.tmdb.ui.dashboard.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.tmdb.databinding.ListItemSearchBinding
import com.example.tmdb.databinding.ListItemSearchTrendingBinding
import com.example.tmdb.response.search.Result
import com.example.tmdb.ui.dashboard.viewholder.TrendingViewHolder

class SearchTrendingListAdapter(private val context: Context) :
    ListAdapter<Result, TrendingViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            ListItemSearchTrendingBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                val title = if (media_type.contentEquals("movie")) title else name
                binding.textview.text = title
            }
        }
    }
}