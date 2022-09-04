package com.example.tmdb.ui.dashboard.series

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.DashboardMainContentBinding
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.LinearSpaceItemDecoration
import com.example.tmdb.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment :
    BaseFragment<DashboardMainContentBinding, SeriesViewModel>(DashboardMainContentBinding::inflate),
    OnItemClick {

    private lateinit var seriesListAdapter: SeriesListAdapter
    private lateinit var decoration: LinearSpaceItemDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = LinearSpaceItemDecoration(this.requireContext(), 14)
        model.airingTodaySeries.observe(this) {
            binding.cardView1.cardViewTitle.text = getString(R.string.airing_today)
            when (it) {
                is NetworkResult.Success -> {
                    seriesListAdapter = SeriesListAdapter(this.requireContext(), this)
                    binding.cardView1.posterList.apply {
                        adapter = seriesListAdapter
                        addItemDecoration(decoration)
                    }
                    seriesListAdapter.submitList(it.data.results)
                }
                is NetworkResult.Failure -> {
                    binding.cardView1.apply {
                        posterList.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        error.text = it.message
                    }
                }
            }
        }
        model.onAirSeries.observe(this) {
            binding.cardView2.cardViewTitle.text = getString(R.string.on_air)
            when (it) {
                is NetworkResult.Success -> {
                    seriesListAdapter = SeriesListAdapter(this.requireContext(), this)
                    binding.cardView2.posterList.apply {
                        adapter = seriesListAdapter
                        addItemDecoration(decoration)
                    }
                    seriesListAdapter.submitList(it.data.results)
                }
                is NetworkResult.Failure -> {
                    binding.cardView2.apply {
                        posterList.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        error.text = it.message
                    }
                }
            }
        }
        model.popularSeries.observe(this) {
            binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
            when (it) {
                is NetworkResult.Success -> {
                    seriesListAdapter = SeriesListAdapter(this.requireContext(), this)
                    binding.cardView3.posterList.apply {
                        adapter = seriesListAdapter
                        addItemDecoration(decoration)
                    }
                    seriesListAdapter.submitList(it.data.results)
                }
                is NetworkResult.Failure -> {
                    binding.cardView3.apply {
                        posterList.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        error.text = it.message
                    }
                }
            }
        }
        model.topRatedSeries.observe(this) {
            binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
            when (it) {
                is NetworkResult.Success -> {
                    seriesListAdapter = SeriesListAdapter(this.requireContext(), this)
                    binding.cardView4.posterList.apply {
                        adapter = seriesListAdapter
                        addItemDecoration(decoration)
                    }
                    seriesListAdapter.submitList(it.data.results)
                }
                is NetworkResult.Failure -> {
                    binding.cardView4.apply {
                        posterList.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        error.text = it.message
                    }
                }
            }
        }
    }

    override fun getViewModelObject(): SeriesViewModel {
        return ViewModelProvider(this)[SeriesViewModel::class.java]
    }

    override fun onItemClick(id: Int) {
        this.activity?.startActivity(DetailsActivity.newInstance(this.requireActivity(), "tv", id))
    }

}