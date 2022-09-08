package com.example.tmdb.repository

import com.example.tmdb.network.CelebritiesService
import javax.inject.Inject

class CelebritiesRepository @Inject constructor(private val celebritiesService: CelebritiesService) :
    BaseRepository() {

    suspend fun getTrendingCelebs() =
        safeApiCall { celebritiesService.getTrending() }

    suspend fun getPopularCelebs() =
        safeApiCall { celebritiesService.getPopular() }
}