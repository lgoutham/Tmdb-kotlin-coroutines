package com.example.tmdb.ui.dashboard.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ListItemMovieBinding
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.movies.Movie
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.PosterViewHolder

class MoviesListAdapter(private val context: Context, private val onItemClick: OnItemClick) :
    ListAdapter<Movie, PosterViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(
            ListItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                val posterPath: String = ApiConstants.POSTER_URL_IMAGE_W300 + posterPath
                Glide.with(context).load(posterPath)
                    .into(binding.poster)
                holder.itemView.setOnClickListener {
                    onItemClick.onItemClick(id)
                }
            }
        }
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