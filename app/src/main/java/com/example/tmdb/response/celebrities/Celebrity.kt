package com.example.tmdb.response.celebrities

data class Celebrity(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String
)