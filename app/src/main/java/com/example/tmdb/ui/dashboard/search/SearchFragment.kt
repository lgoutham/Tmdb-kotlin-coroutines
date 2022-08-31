package com.example.tmdb.ui.dashboard.search

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {
    override fun initUI(savedInstanceState: Bundle?) {

    }

    override fun getViewModelObject(): SearchViewModel {
        return ViewModelProvider(this)[SearchViewModel::class.java]
    }

}