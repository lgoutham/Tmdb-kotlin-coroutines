package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.network.MovieService
import com.example.tmdb.response.movies.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    private var _nowPlayingMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    val nowPlayingMovies: LiveData<MovieResponse>
        get() = _nowPlayingMovies
    private var _upcomingMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    val upcomingMovies: LiveData<MovieResponse>
        get() = _upcomingMovies
    private var _popularMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    val popularMovies: LiveData<MovieResponse>
        get() = _popularMovies
    private var _topRatedMovies: MutableLiveData<MovieResponse> = MutableLiveData()
    val topRatedMovies: LiveData<MovieResponse>
        get() = _topRatedMovies

    suspend fun getNowPlayingMovies() {
        withContext(Dispatchers.IO) {
            val response = movieService.getNowPlayingMovies()
            if (response.isSuccessful) {
                _nowPlayingMovies.postValue(response.body())
            }
        }
    }

    suspend fun getUpcomingMovies() {
        withContext(Dispatchers.IO) {
            val response = movieService.getUpcomingMovies()
            if (response.isSuccessful) {
                _upcomingMovies.postValue(response.body())
            }
        }
    }

    suspend fun getPopularMovies() {
        withContext(Dispatchers.IO) {
            val response = movieService.getPopularMovies()
            if (response.isSuccessful) {
                _popularMovies.postValue(response.body())
            }
        }
    }

    suspend fun getTopRatedMovies() {
        withContext(Dispatchers.IO) {
            val response = movieService.getTopRatedMovies()
            if (response.isSuccessful) {
                _topRatedMovies.postValue(response.body())
            }
        }
    }
}