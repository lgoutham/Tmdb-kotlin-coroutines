package com.example.tmdb.ui.dashboard.series

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.DashboardMainContentBinding
import com.example.tmdb.ui.dashboard.viewholder.LinearSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment :
    BaseFragment<DashboardMainContentBinding, SeriesViewModel>(DashboardMainContentBinding::inflate) {

    private lateinit var seriesListAdapter: SeriesListAdapter
    private lateinit var decoration: LinearSpaceItemDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = LinearSpaceItemDecoration(this.requireContext(), 14)
        model.airingTodaySeries.observe(this) {
            seriesListAdapter = SeriesListAdapter(this.requireContext())
            binding.cardView1.cardViewTitle.text = getString(R.string.airing_today)
            binding.cardView1.posterList.adapter = seriesListAdapter
            binding.cardView1.posterList.addItemDecoration(decoration)
            seriesListAdapter.submitList(it.results)
        }
        model.onAirSeries.observe(this) {
            seriesListAdapter = SeriesListAdapter(this.requireContext())
            binding.cardView2.cardViewTitle.text = getString(R.string.on_air)
            binding.cardView2.posterList.adapter = seriesListAdapter
            binding.cardView2.posterList.addItemDecoration(decoration)
            seriesListAdapter.submitList(it.results)
        }
        model.popularSeries.observe(this) {
            seriesListAdapter = SeriesListAdapter(this.requireContext())
            binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
            binding.cardView3.posterList.adapter = seriesListAdapter
            binding.cardView3.posterList.addItemDecoration(decoration)
            seriesListAdapter.submitList(it.results)
        }
        model.topRatedSeries.observe(this) {
            seriesListAdapter = SeriesListAdapter(this.requireContext())
            binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
            binding.cardView4.posterList.adapter = seriesListAdapter
            binding.cardView4.posterList.addItemDecoration(decoration)
            seriesListAdapter.submitList(it.results)
        }
    }

    override fun getViewModelObject(): SeriesViewModel {
        return ViewModelProvider(this)[SeriesViewModel::class.java]
    }

}