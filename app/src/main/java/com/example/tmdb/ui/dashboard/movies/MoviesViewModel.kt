package com.example.tmdb.ui.dashboard.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.MovieRepository
import com.example.tmdb.response.movies.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    private var _nowPlayingMovies: MutableLiveData<NetworkResult<MovieResponse>> = MutableLiveData()
    val nowPlayingMovies: LiveData<NetworkResult<MovieResponse>> = _nowPlayingMovies
    private var _upcomingMovies: MutableLiveData<NetworkResult<MovieResponse>> = MutableLiveData()
    val upcomingMovies: LiveData<NetworkResult<MovieResponse>> = _upcomingMovies
    private var _popularMovies: MutableLiveData<NetworkResult<MovieResponse>> = MutableLiveData()
    val popularMovies: LiveData<NetworkResult<MovieResponse>> = _popularMovies
    private var _topRatedMovies: MutableLiveData<NetworkResult<MovieResponse>> = MutableLiveData()
    val topRatedMovies: LiveData<NetworkResult<MovieResponse>> = _topRatedMovies

    init {
        viewModelScope.launch {
            _nowPlayingMovies.postValue(movieRepository.getNowPlayingMovies())
            _upcomingMovies.postValue(movieRepository.getUpcomingMovies())
            _popularMovies.postValue(movieRepository.getPopularMovies())
            _topRatedMovies.postValue(movieRepository.getTopRatedMovies())
        }
    }
}