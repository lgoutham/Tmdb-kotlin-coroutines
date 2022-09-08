package com.example.tmdb.ui.dashboard.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.databinding.ListItemMovieBinding
import com.example.tmdb.databinding.ListItemSearchBinding
import com.example.tmdb.databinding.ListItemSearchTrendingBinding

class PosterViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

class CelebrityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val celebrity: AppCompatImageView = itemView.findViewById(R.id.celebrity)
    val name: AppCompatTextView = itemView.findViewById(R.id.name)
    val knownFor: AppCompatTextView = itemView.findViewById(R.id.known_for)
}

class SearchResultViewHolder(val binding: ListItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root)

class TrendingViewHolder(val binding: ListItemSearchTrendingBinding) :
    RecyclerView.ViewHolder(binding.root)