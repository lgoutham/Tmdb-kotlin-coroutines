package com.example.tmdb.ui.dashboard.series

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.tvseries.TvSeries
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.PosterViewHolder

class SeriesListAdapter(private val context: Context, private val onItemClick: OnItemClick) :
    ListAdapter<TvSeries, PosterViewHolder>(SeriesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val series = getItem(position)
        val posterPath: String = ApiConstants.POSTER_URL_IMAGE_W300 + series.posterPath
        Glide.with(context).load(posterPath)
            .into(holder.poster)
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(series.id)
        }
    }

    class SeriesDiffUtil : DiffUtil.ItemCallback<TvSeries>() {
        override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem == newItem
        }
    }
}