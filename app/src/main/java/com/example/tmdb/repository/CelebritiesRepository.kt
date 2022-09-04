package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.network.CelebritiesService
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.response.celebrities.CelebritiesResponse
import javax.inject.Inject

class CelebritiesRepository @Inject constructor(private val celebritiesService: CelebritiesService) :
    BaseRepository() {

    suspend fun getTrendingCelebs(): NetworkResult<CelebritiesResponse> =
        safeApiCall { celebritiesService.getTrending() }

    suspend fun getPopularCelebs(): NetworkResult<CelebritiesResponse> =
        safeApiCall { celebritiesService.getPopular() }
}