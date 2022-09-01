package com.example.tmdb.ui.dashboard.celebrities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import com.example.tmdb.ui.dashboard.viewholder.GridItemSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment :
    BaseFragment<FragmentCelebritiesBinding, CelebritiesViewModel>(FragmentCelebritiesBinding::inflate) {

    private lateinit var decoration: GridItemSpaceDecoration

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = GridItemSpaceDecoration(this.requireContext(), 14, 2)
        val popularCelebritiesListAdapter = PopularCelebritiesListAdapter(this.requireContext())
        val trendingCelebritiesListAdapter = TrendingCelebritiesListAdapter(this.requireContext())
        model.popular.observe(this) {
            binding.popular.text = getString(R.string.most_popular)
            binding.popularList.adapter = popularCelebritiesListAdapter
            binding.popularList.layoutManager =
                GridLayoutManager(this.requireContext(), 2, RecyclerView.HORIZONTAL, false)
            popularCelebritiesListAdapter.submitList(it.results)
            binding.popularList.addItemDecoration(decoration)
        }

        model.trending.observe(this) {response ->
            binding.trending.text = getString(R.string.trending)
            binding.trendingList.adapter = trendingCelebritiesListAdapter
            binding.trendingList.layoutManager =
                GridLayoutManager(this.requireContext(), 2, RecyclerView.HORIZONTAL, false)
            trendingCelebritiesListAdapter.submitList(response.results.filter {
                it.profile_path != null
            })
            binding.trendingList.addItemDecoration(decoration)
        }
    }

    override fun getViewModelObject(): CelebritiesViewModel {
        return ViewModelProvider(this)[CelebritiesViewModel::class.java]
    }

}