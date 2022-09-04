package com.example.tmdb.repository

import com.example.tmdb.network.NetworkResult
import com.example.tmdb.network.TvSeriesService
import com.example.tmdb.response.tvseries.TvSeriesResponse
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val tvSeriesService: TvSeriesService) :
    BaseRepository() {

    suspend fun getAiringTodaySeries(): NetworkResult<TvSeriesResponse> =
        safeApiCall { tvSeriesService.getAiringTodaySeries() }

    suspend fun getOnAirSeries(): NetworkResult<TvSeriesResponse> =
        safeApiCall { tvSeriesService.getOnAirSeries() }

    suspend fun getPopularSeries(): NetworkResult<TvSeriesResponse> =
        safeApiCall { tvSeriesService.getPopularSeries() }

    suspend fun getTopRatedSeries(): NetworkResult<TvSeriesResponse> =
        safeApiCall { tvSeriesService.getTopRatedSeries() }

}