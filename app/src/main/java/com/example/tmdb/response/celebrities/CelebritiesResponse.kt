package com.example.tmdb.response.celebrities

data class CelebritiesResponse(
    val page: Int,
    val results: List<Celebrity>,
    val total_pages: Int,
    val total_results: Int
)