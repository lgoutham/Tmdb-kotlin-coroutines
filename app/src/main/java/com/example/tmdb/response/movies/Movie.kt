package com.example.tmdb.response.movies

import com.google.gson.annotations.SerializedName

data class Movie(
    @field:SerializedName("poster_path") var posterPath: String,
    @field:SerializedName("adult") var isAdult: Boolean,
    var overview: String,
    @field:SerializedName("release_date") var releaseDate: String,
    @field:SerializedName("genres") var genreIds: ArrayList<Genre>,
    var id: Int,
    @field:SerializedName("original_title") var originalTitle: String,
    @field:SerializedName("original_language") var originalLanguage: String,
    var title: String,
    @field:SerializedName("backdrop_path") var backdropPath: String,
    var popularity: Double,
    @field:SerializedName("vote_count") var voteCount: Int,
    var video: Boolean,
    @field:SerializedName("vote_average") var voteAverage: Double
)