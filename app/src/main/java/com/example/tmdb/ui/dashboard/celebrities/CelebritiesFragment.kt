package com.example.tmdb.ui.dashboard.celebrities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.ui.dashboard.OnItemClick
import com.example.tmdb.ui.dashboard.viewholder.GridItemSpaceDecoration
import com.example.tmdb.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment :
    BaseFragment<FragmentCelebritiesBinding, CelebritiesViewModel>(FragmentCelebritiesBinding::inflate),
    OnItemClick {

    override fun initUI(savedInstanceState: Bundle?) {
        val decoration = GridItemSpaceDecoration(this.requireContext(), 14, 2)
        val popularCelebritiesListAdapter =
            PopularCelebritiesListAdapter(this.requireContext(), this)
        val trendingCelebritiesListAdapter =
            TrendingCelebritiesListAdapter(this.requireContext(), this)
        model.popular.observe(this) {
            binding.popular.text = getString(R.string.most_popular)
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

        model.trending.observe(this) { response ->
            binding.trending.text = getString(R.string.trending)
            when (response) {
                is NetworkResult.Success -> {
                    binding.trendingList.apply {
                        adapter = trendingCelebritiesListAdapter
                        layoutManager =
                            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
                        addItemDecoration(decoration)
                    }
                    trendingCelebritiesListAdapter.submitList(response.data.results.filter {
                        it.profile_path != null
                    })
                }
                else -> {}
            }
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