package com.example.tmdb.ui.dashboard.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.tmdb.R
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.movies.Movie
import com.example.tmdb.ui.dashboard.viewholder.PosterViewHolder

class MoviesListAdapter(private val context: Context) :
    ListAdapter<Movie, PosterViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val movie = getItem(position)
        val posterPath: String = ApiConstants.POSTER_URL_IMAGE_W300 + movie.posterPath
        Glide.with(context).load(posterPath)
            .into(holder.poster)
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}