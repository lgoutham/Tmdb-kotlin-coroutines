package com.example.tmdb.ui.dashboard.search

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.SearchRepository
import com.example.tmdb.response.search.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel() {

    private var _search: MutableStateFlow<NetworkResult<SearchResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val search: StateFlow<NetworkResult<SearchResponse>> = _search
    private var _trending: MutableStateFlow<NetworkResult<SearchResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val trending: StateFlow<NetworkResult<SearchResponse>> = _trending

    init {
        viewModelScope.launch {
            searchRepository.getTrendingAllWeek().collect {
                _trending.value = it
            }
        }
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch {
            searchRepository.getSearchResults(query).collect {
                _search.value = it
            }
        }
    }
}