package com.example.tmdb.ui.dashboard.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ListItemSearchBinding
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.search.Result
import com.example.tmdb.ui.dashboard.viewholder.SearchResultViewHolder

class SearchResultListAdapter(private val context: Context) :
    ListAdapter<Result, SearchResultViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            ListItemSearchBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                val backdropPath: String = ApiConstants.POSTER_URL_IMAGE_W185 + backdrop_path
                Glide.with(context).load(backdropPath).into(binding.poster)
                val title = if (media_type.contentEquals("movie")) title else name
                binding.name.text = title
            }
        }
    }
}