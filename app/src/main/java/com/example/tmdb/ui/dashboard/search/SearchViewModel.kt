package com.example.tmdb.ui.dashboard.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.SearchRepository
import com.example.tmdb.response.search.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    BaseViewModel() {

    private var _search: MutableLiveData<NetworkResult<SearchResponse>> = MutableLiveData()
    val search: LiveData<NetworkResult<SearchResponse>> = _search
    private var _trending: MutableLiveData<NetworkResult<SearchResponse>> = MutableLiveData()
    val trending: LiveData<NetworkResult<SearchResponse>> = _trending

    init {
        viewModelScope.launch {
            _trending.postValue(searchRepository.getTrendingAllWeek())
        }
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch {
            _search.postValue(searchRepository.getSearchResults(query))
        }
    }
}