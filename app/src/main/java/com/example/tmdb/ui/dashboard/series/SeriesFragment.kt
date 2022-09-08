package com.example.tmdb.ui.dashboard.series

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.DashboardMainContentBinding
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.response.tvseries.TvSeriesResponse
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
        lifecycleScope.launchWhenStarted {
            model.airingTodaySeries.collect {
                binding.cardView1.cardViewTitle.text = getString(R.string.airing_today)
                handleAiringTodayResponse(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            model.onAirSeries.collect {
                binding.cardView2.cardViewTitle.text = getString(R.string.on_air)
                handleOnAirResponse(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            model.popularSeries.collect {
                binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
                handleMostPopularResponse(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            model.topRatedSeries.collect {
                binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
                handleTopRatedResponse(it)
            }
        }
    }

    private fun handleAiringTodayResponse(it: NetworkResult<TvSeriesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                seriesListAdapter = SeriesListAdapter(requireContext(), this)
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
            else -> {}
        }
    }

    private fun handleOnAirResponse(it: NetworkResult<TvSeriesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                seriesListAdapter = SeriesListAdapter(requireContext(), this)
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
            else -> {}
        }
    }

    private fun handleMostPopularResponse(it: NetworkResult<TvSeriesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                seriesListAdapter = SeriesListAdapter(requireContext(), this)
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
            else -> {}
        }
    }

    private fun handleTopRatedResponse(it: NetworkResult<TvSeriesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                seriesListAdapter = SeriesListAdapter(requireContext(), this)
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
            else -> {}
        }
    }

    override fun getViewModelObject(): SeriesViewModel {
        return ViewModelProvider(this)[SeriesViewModel::class.java]
    }

    override fun onItemClick(id: Int) {
        this.activity?.startActivity(DetailsActivity.newInstance(this.requireActivity(), "tv", id))
    }

}