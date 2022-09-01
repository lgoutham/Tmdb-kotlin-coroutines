package com.example.tmdb.ui.dashboard.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentSearchBinding
import com.example.tmdb.response.search.Result
import com.example.tmdb.ui.dashboard.viewholder.GridItemSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {

    private lateinit var adapter: SearchTrendingListAdapter
    private lateinit var trendingResults: List<Result>

    override fun initUI(savedInstanceState: Bundle?) {
        adapter = SearchTrendingListAdapter(this.requireContext())
        model.trending.observe(this) {
            trendingResults = it.results
            setTrendingResults(trendingResults)
        }
        model.search.observe(this) {
            val adapter = SearchResultListAdapter(this.requireContext())
            val decoration =
                GridItemSpaceDecoration(this.requireContext(), 14, 2, RecyclerView.VERTICAL)
            binding.searchList.adapter = adapter
            binding.searchList.addItemDecoration(decoration)
            binding.searchList.layoutManager =
                GridLayoutManager(this.requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter.submitList(filter(it.results))
            binding.trending.visibility = View.GONE
        }
        binding.search.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.clear.visibility = View.VISIBLE
                model.getSearchResults(view.text.toString())
                view.hideKeyboard()
            }
            true
        }
        binding.clear.setOnClickListener {
            binding.search.text?.clear()
            binding.trending.visibility = View.VISIBLE
            binding.clear.visibility = View.GONE
            setTrendingResults(trendingResults)
        }
    }

    private fun setTrendingResults(it: List<Result>) {
        binding.searchList.adapter = adapter
        binding.searchList.layoutManager =
            LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        adapter.submitList(it)
    }

    private fun filter(list: List<Result>): List<Result> {
        return list.filter {
            it.media_type != "person"
        }.filter {
            it.backdrop_path != null
        }
    }

    override fun getViewModelObject(): SearchViewModel {
        return ViewModelProvider(this)[SearchViewModel::class.java]
    }

}