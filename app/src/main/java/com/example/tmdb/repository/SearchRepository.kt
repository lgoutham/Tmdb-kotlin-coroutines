package com.example.tmdb.repository

import com.example.tmdb.network.NetworkResult
import com.example.tmdb.network.SearchService
import com.example.tmdb.response.search.SearchResponse
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchService: SearchService) :
    BaseRepository() {

    suspend fun getTrendingAllWeek(): NetworkResult<SearchResponse> =
        safeApiCall { searchService.getTrendingAllWeek() }

    suspend fun getSearchResults(query: String): NetworkResult<SearchResponse> =
        safeApiCall { searchService.getSearchResults(query, "en-US") }
}