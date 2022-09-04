package com.example.tmdb.repository

import com.example.tmdb.network.MovieService
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.response.movies.MovieResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) :
    BaseRepository() {

    suspend fun getNowPlayingMovies(): NetworkResult<MovieResponse> =
        safeApiCall { movieService.getNowPlayingMovies() }

    suspend fun getUpcomingMovies(): NetworkResult<MovieResponse> =
        safeApiCall { movieService.getUpcomingMovies() }

    suspend fun getPopularMovies(): NetworkResult<MovieResponse> =
        safeApiCall { movieService.getPopularMovies() }

    suspend fun getTopRatedMovies(): NetworkResult<MovieResponse> =
        safeApiCall { movieService.getTopRatedMovies() }
}