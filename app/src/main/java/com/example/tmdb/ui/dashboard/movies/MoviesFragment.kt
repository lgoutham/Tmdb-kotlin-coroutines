package com.example.tmdb.ui.dashboard.movies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.DashboardMainContentBinding
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.response.movies.MovieResponse
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.LinearSpaceItemDecoration
import com.example.tmdb.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment :
    BaseFragment<DashboardMainContentBinding, MoviesViewModel>(DashboardMainContentBinding::inflate),
    OnItemClick {

    private lateinit var moviesListAdapter: MoviesListAdapter
    private lateinit var decoration: LinearSpaceItemDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = LinearSpaceItemDecoration(this.requireContext(), 14)
        lifecycleScope.launchWhenStarted {
            model.nowPlayingMovies.collect {
                binding.cardView1.cardViewTitle.text = getString(R.string.now_playing)
                handleNowPlayingResponse(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            model.upcomingMovies.collect {
                binding.cardView2.cardViewTitle.text = getString(R.string.upcoming)
                handleUpcomingResponse(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            model.popularMovies.collect {
                binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
                handlePopularResponse(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            model.topRatedMovies.collect {
                binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
                handleTopRatedResponse(it)
            }
        }
    }

    private fun handleNowPlayingResponse(it: NetworkResult<MovieResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                moviesListAdapter = MoviesListAdapter(this.requireContext(), this)
                binding.cardView1.posterList.apply {
                    adapter = moviesListAdapter
                    addItemDecoration(decoration)
                }
                moviesListAdapter.submitList(it.data.results)
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

    private fun handleUpcomingResponse(it: NetworkResult<MovieResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                moviesListAdapter = MoviesListAdapter(this.requireContext(), this)
                binding.cardView2.posterList.apply {
                    adapter = moviesListAdapter
                    addItemDecoration(decoration)
                }
                moviesListAdapter.submitList(it.data.results)
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

    private fun handlePopularResponse(it: NetworkResult<MovieResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                moviesListAdapter = MoviesListAdapter(this.requireContext(), this)
                binding.cardView3.posterList.apply {
                    adapter = moviesListAdapter
                    addItemDecoration(decoration)
                }
                moviesListAdapter.submitList(it.data.results)
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

    private fun handleTopRatedResponse(it: NetworkResult<MovieResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                moviesListAdapter = MoviesListAdapter(this.requireContext(), this)
                binding.cardView4.posterList.apply {
                    adapter = moviesListAdapter
                    addItemDecoration(decoration)
                }
                moviesListAdapter.submitList(it.data.results)
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

    override fun getViewModelObject(): MoviesViewModel {
        return ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onItemClick(id: Int) {
        this.activity?.startActivity(
            DetailsActivity.newInstance(
                this.requireActivity(),
                "movie",
                id
            )
        )
    }

}