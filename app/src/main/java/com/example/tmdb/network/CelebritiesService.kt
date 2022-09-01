package com.example.tmdb.network

import com.example.tmdb.response.celebrities.CelebritiesResponse
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CelebritiesService {

    @GET("trending/person/week")
    suspend fun getTrending(): Response<CelebritiesResponse>

    @GET("person/popular")
    suspend fun getPopular(): Response<CelebritiesResponse>
}