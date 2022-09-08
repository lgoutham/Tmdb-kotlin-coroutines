package com.example.tmdb.ui.dashboard.movies

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.response.movies.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    private var _nowPlayingMovies: MutableStateFlow<NetworkResult<MovieResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val nowPlayingMovies: StateFlow<NetworkResult<MovieResponse>> = _nowPlayingMovies
    private var _upcomingMovies: MutableStateFlow<NetworkResult<MovieResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val upcomingMovies: StateFlow<NetworkResult<MovieResponse>> = _upcomingMovies
    private var _popularMovies: MutableStateFlow<NetworkResult<MovieResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val popularMovies: StateFlow<NetworkResult<MovieResponse>> = _popularMovies
    private var _topRatedMovies: MutableStateFlow<NetworkResult<MovieResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val topRatedMovies: StateFlow<NetworkResult<MovieResponse>> = _topRatedMovies

    init {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies().collect {
                _nowPlayingMovies.value = it
            }
            movieRepository.getUpcomingMovies().collect {
                _upcomingMovies.value = it
            }
            movieRepository.getPopularMovies().collect {
                _popularMovies.value = it
            }
            movieRepository.getTopRatedMovies().collect {
                _topRatedMovies.value = it
            }
        }
    }
}