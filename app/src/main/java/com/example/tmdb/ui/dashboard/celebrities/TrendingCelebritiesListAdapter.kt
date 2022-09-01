package com.example.tmdb.ui.dashboard.celebrities

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.R
import com.example.tmdb.network.ApiConstants
import com.example.tmdb.response.celebrities.Celebrity
import com.example.tmdb.ui.dashboard.viewholder.CelebrityViewHolder

class TrendingCelebritiesListAdapter(private val context: Context) :
    ListAdapter<Celebrity, CelebrityViewHolder>(
        CelebrityDiffUtil()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CelebrityViewHolder {
        return CelebrityViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.list_item_trending_celebrity, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: CelebrityViewHolder,
        position: Int
    ) {
        val celebrity = getItem(position)
        holder.name.text = celebrity.name
        val posterPath: String = ApiConstants.POSTER_URL_IMAGE_W185 + celebrity.profile_path
        Glide.with(context).load(posterPath).apply(RequestOptions.circleCropTransform()).into(holder.celebrity)
        holder.knownFor.text = celebrity.known_for_department
    }
}