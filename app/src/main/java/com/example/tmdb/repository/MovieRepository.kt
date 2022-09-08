package com.example.tmdb.repository

import com.example.tmdb.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) :
    BaseRepository() {

    suspend fun getNowPlayingMovies() =
        safeApiCall { movieService.getNowPlayingMovies() }

    suspend fun getUpcomingMovies() =
        safeApiCall { movieService.getUpcomingMovies() }

    suspend fun getPopularMovies() =
        safeApiCall { movieService.getPopularMovies() }

    suspend fun getTopRatedMovies() =
        safeApiCall { movieService.getTopRatedMovies() }
}