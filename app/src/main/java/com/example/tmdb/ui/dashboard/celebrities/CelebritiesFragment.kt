package com.example.tmdb.ui.dashboard.celebrities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.response.celebrities.CelebritiesResponse
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.GridItemSpaceDecoration
import com.example.tmdb.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment :
    BaseFragment<FragmentCelebritiesBinding, CelebritiesViewModel>(FragmentCelebritiesBinding::inflate),
    OnItemClick {

    private lateinit var decoration: GridItemSpaceDecoration
    private lateinit var popularCelebritiesListAdapter: PopularCelebritiesListAdapter
    private lateinit var trendingCelebritiesListAdapter: TrendingCelebritiesListAdapter

    override fun initUI(savedInstanceState: Bundle?) {
        decoration = GridItemSpaceDecoration(this.requireContext(), 14, 2)
        popularCelebritiesListAdapter =
            PopularCelebritiesListAdapter(this.requireContext(), this)
        trendingCelebritiesListAdapter =
            TrendingCelebritiesListAdapter(this.requireContext(), this)
        lifecycleScope.launchWhenStarted {
            model.popular.collect {
                binding.popular.text = getString(R.string.most_popular)
                handlePopularResponse(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            model.trending.collect {
                binding.trending.text = getString(R.string.trending)
                handleTrendingResponse(it)
            }
        }
    }

    private fun handleTrendingResponse(it: NetworkResult<CelebritiesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                binding.trendingList.apply {
                    adapter = trendingCelebritiesListAdapter
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
                    addItemDecoration(decoration)
                }
                trendingCelebritiesListAdapter.submitList(it.data.results.filter {
                    it.profile_path != null
                })
            }
            else -> {}
        }
    }

    private fun handlePopularResponse(it: NetworkResult<CelebritiesResponse>) {
        when (it) {
            is NetworkResult.Success -> {
                binding.popularList.apply {
                    adapter = popularCelebritiesListAdapter
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
                    addItemDecoration(decoration)
                }
                popularCelebritiesListAdapter.submitList(it.data.results)
            }
            else -> {}
        }
    }

    override fun getViewModelObject(): CelebritiesViewModel {
        return ViewModelProvider(this)[CelebritiesViewModel::class.java]
    }

    override fun onItemClick(id: Int) {
        this.activity?.startActivity(
            DetailsActivity.newInstance(
                this.requireActivity(),
                "person",
                id
            )
        )
    }

}