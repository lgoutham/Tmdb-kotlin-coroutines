package com.example.tmdb.ui.dashboard.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R

class CelebrityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val celebrity: AppCompatImageView = itemView.findViewById(R.id.celebrity)
    val name: AppCompatTextView = itemView.findViewById(R.id.name)
    val knownFor: AppCompatTextView = itemView.findViewById(R.id.known_for)
}