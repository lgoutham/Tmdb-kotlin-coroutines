package com.example.tmdb.repository

import com.example.tmdb.network.TvSeriesService
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val tvSeriesService: TvSeriesService) :
    BaseRepository() {

    suspend fun getAiringTodaySeries() =
        safeApiCall { tvSeriesService.getAiringTodaySeries() }

    suspend fun getOnAirSeries() =
        safeApiCall { tvSeriesService.getOnAirSeries() }

    suspend fun getPopularSeries() =
        safeApiCall { tvSeriesService.getPopularSeries() }

    suspend fun getTopRatedSeries() =
        safeApiCall { tvSeriesService.getTopRatedSeries() }

}