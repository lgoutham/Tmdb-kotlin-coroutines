package com.example.tmdb.network

import com.example.tmdb.response.tvseries.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TvSeriesService {

    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(): Response<TvSeriesResponse>

    @GET("tv/on_the_air")
    suspend fun getOnAirSeries(): Response<TvSeriesResponse>

    @GET("tv/popular")
    suspend fun getPopularSeries(): Response<TvSeriesResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(): Response<TvSeriesResponse>
}