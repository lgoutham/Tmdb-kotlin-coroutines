package com.example.tmdb.response.tvseries

import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
    var page: Int,
    var results: List<TvSeries>,

    @SerializedName("total_results")
    var totalResults: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0
)