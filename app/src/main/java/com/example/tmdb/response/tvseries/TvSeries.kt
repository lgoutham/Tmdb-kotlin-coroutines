package com.example.tmdb.response.tvseries

import com.google.gson.annotations.SerializedName

data class TvSeries(
    @field:SerializedName("original_Name") var originalTitle: String,
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("vote_count") var voteCount: Int,
    @field:SerializedName("vote_average") var voteAverage: Double,
    @field:SerializedName("poster_path") var posterPath: String,
    @field:SerializedName("first_air_date") var firstAirDate: String,
    @field:SerializedName("popularity") var popularity: Double,
    @field:SerializedName("original_language") var originalLanguage: String,
    @field:SerializedName("backdrop_path") var backdropPath: String,
    @field:SerializedName("overview") var overview: String
)