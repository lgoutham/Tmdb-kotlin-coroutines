package com.example.tmdb.repository

import com.example.tmdb.network.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchService: SearchService) :
    BaseRepository() {

    suspend fun getTrendingAllWeek() =
        safeApiCall { searchService.getTrendingAllWeek() }

    suspend fun getSearchResults(query: String) =
        safeApiCall { searchService.getSearchResults(query, "en-US") }
}