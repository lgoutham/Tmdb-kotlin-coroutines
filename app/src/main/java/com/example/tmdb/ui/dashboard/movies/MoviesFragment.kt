package com.example.tmdb.ui.dashboard.movies

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
class MoviesFragment :
    BaseFragment<DashboardMainContentBinding, MoviesViewModel>(DashboardMainContentBinding::inflate),
    OnItemClick {

    private lateinit var moviesListAdapter: MoviesListAdapter
    private lateinit var decoration: LinearSpaceItemDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = LinearSpaceItemDecoration(this.requireContext(), 14)
        model.nowPlayingMovies.observe(this) {
            binding.cardView1.cardViewTitle.text = getString(R.string.now_playing)
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
            }
        }

        model.upcomingMovies.observe(this) {
            binding.cardView2.cardViewTitle.text = getString(R.string.upcoming)
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
            }
        }

        model.popularMovies.observe(this) {
            binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
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
            }
        }

        model.topRatedMovies.observe(this) {
            binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
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
            }
        }
    }

    override fun getViewModelObject(): MoviesViewModel {
        return ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onItemClick(id: Int) {
        this.activity?.startActivity(DetailsActivity.newInstance(this.requireActivity(), "movie", id))
    }

}