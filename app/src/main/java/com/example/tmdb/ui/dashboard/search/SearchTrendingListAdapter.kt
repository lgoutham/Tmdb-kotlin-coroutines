package com.example.tmdb.ui.dashboard.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.response.search.Result

class SearchTrendingListAdapter(private val context: Context) :
    ListAdapter<Result, SearchTrendingListAdapter.TrendingViewHolder>(SearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_search_trending, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val title: String
        val item = getItem(position)
        title = if (item.media_type.contentEquals("movie")) item.title else item.name
        holder.textView.text = title
    }

    class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: AppCompatTextView = itemView.findViewById(R.id.textview)
    }
}