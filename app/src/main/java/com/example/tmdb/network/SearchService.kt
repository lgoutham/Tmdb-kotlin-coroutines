package com.example.tmdb.network

import com.example.tmdb.response.search.SearchResponse
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface SearchService {

    @GET("trending/all/week")
    suspend fun getTrendingAllWeek(): Response<SearchResponse>

    @GET("search/multi")
    suspend fun getSearchResults(@Query("query") query: String, @Query("language") language: String): Response<SearchResponse>
}