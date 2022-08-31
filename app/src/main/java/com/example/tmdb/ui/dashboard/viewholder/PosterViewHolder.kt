package com.example.tmdb.ui.dashboard.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R

class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val poster: AppCompatImageView = itemView.findViewById(R.id.poster)
}