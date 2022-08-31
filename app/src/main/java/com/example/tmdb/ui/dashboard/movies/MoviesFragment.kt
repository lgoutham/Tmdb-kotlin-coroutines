package com.example.tmdb.ui.dashboard.movies

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.DashboardMainContentBinding
import com.example.tmdb.ui.dashboard.viewholder.LinearSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment :
    BaseFragment<DashboardMainContentBinding, MoviesViewModel>(DashboardMainContentBinding::inflate) {

    private lateinit var moviesListAdapter: MoviesListAdapter
    private lateinit var decoration: LinearSpaceItemDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = LinearSpaceItemDecoration(this.requireContext(), 14)
        model.nowPlayingMovies.observe(this) {
            moviesListAdapter = MoviesListAdapter(this.requireContext())
            binding.cardView1.cardViewTitle.text = getString(R.string.now_playing)
            binding.cardView1.posterList.adapter = moviesListAdapter
            binding.cardView1.posterList.addItemDecoration(decoration)
            moviesListAdapter.submitList(it.results)
        }

        model.upcomingMovies.observe(this) {
            moviesListAdapter = MoviesListAdapter(this.requireContext())
            binding.cardView2.cardViewTitle.text = getString(R.string.upcoming)
            binding.cardView2.posterList.adapter = moviesListAdapter
            binding.cardView2.posterList.addItemDecoration(decoration)
            moviesListAdapter.submitList(it.results)
        }

        model.popularMovies.observe(this) {
            moviesListAdapter = MoviesListAdapter(this.requireContext())
            binding.cardView3.cardViewTitle.text = getString(R.string.most_popular)
            binding.cardView3.posterList.adapter = moviesListAdapter
            binding.cardView3.posterList.addItemDecoration(decoration)
            moviesListAdapter.submitList(it.results)
        }

        model.topRatedMovies.observe(this) {
            moviesListAdapter = MoviesListAdapter(this.requireContext())
            binding.cardView4.cardViewTitle.text = getString(R.string.top_rated)
            binding.cardView4.posterList.adapter = moviesListAdapter
            binding.cardView4.posterList.addItemDecoration(decoration)
            moviesListAdapter.submitList(it.results)
        }
    }

    override fun getViewModelObject(): MoviesViewModel {
        return ViewModelProvider(this)[MoviesViewModel::class.java]
    }

}