package com.example.tmdb.ui.dashboard.search

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel() {

    val search = searchRepository.search
    val trending = searchRepository.trending

    init {
        viewModelScope.launch {
            searchRepository.getTrendingAllWeek()
        }
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch {
            searchRepository.getSearchResults(query)
        }
    }
}