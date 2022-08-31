package com.example.tmdb.ui.dashboard.movies

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    val nowPlayingMovies = movieRepository.nowPlayingMovies
    val upcomingMovies = movieRepository.upcomingMovies
    val popularMovies = movieRepository.popularMovies
    val topRatedMovies = movieRepository.topRatedMovies

    init {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies()
            movieRepository.getUpcomingMovies()
            movieRepository.getPopularMovies()
            movieRepository.getTopRatedMovies()
        }
    }
}