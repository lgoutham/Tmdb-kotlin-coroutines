package com.example.tmdb.ui.dashboard.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.search.Result

class SearchResultListAdapter(private val context: Context) :
    ListAdapter<Result, SearchResultListAdapter.SearchResultViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        val backdropPath: String = ApiConstants.POSTER_URL_IMAGE_W185 + item.backdrop_path
        Glide.with(context).load(backdropPath).into(holder.image)
        val title = if (item.media_type.contentEquals("movie")) item.title else item.name
        holder.name.text = title
    }

    class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.poster)
        val name: AppCompatTextView = itemView.findViewById(R.id.name)
    }
}