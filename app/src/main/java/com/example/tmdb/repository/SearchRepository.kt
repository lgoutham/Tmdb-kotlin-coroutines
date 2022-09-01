package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.network.SearchService
import com.example.tmdb.response.search.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchService: SearchService) {

    private var _search: MutableLiveData<SearchResponse> = MutableLiveData()
    val search: LiveData<SearchResponse>
        get() = _search

    private var _trending: MutableLiveData<SearchResponse> = MutableLiveData()
    val trending: LiveData<SearchResponse>
        get() = _trending

    suspend fun getTrendingAllWeek() {
        withContext(Dispatchers.IO) {
            val response = searchService.getTrendingAllWeek()
            if (response.isSuccessful) {
                _trending.postValue(response.body())
            }
        }
    }

    suspend fun getSearchResults(query: String) {
        withContext(Dispatchers.IO) {
            val response = searchService.getSearchResults(query, "en-US")
            if (response.isSuccessful) {
                _search.postValue(response.body())
            }
        }
    }
}