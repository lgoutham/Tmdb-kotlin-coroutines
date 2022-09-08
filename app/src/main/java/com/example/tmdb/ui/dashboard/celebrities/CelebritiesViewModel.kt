package com.example.tmdb.ui.dashboard.celebrities

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.CelebritiesRepository
import com.example.tmdb.response.celebrities.CelebritiesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel @Inject constructor(private val celebritiesRepository: CelebritiesRepository) :
    BaseViewModel() {

    private var _trending: MutableStateFlow<NetworkResult<CelebritiesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val trending: StateFlow<NetworkResult<CelebritiesResponse>> = _trending
    private var _popular: MutableStateFlow<NetworkResult<CelebritiesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val popular: StateFlow<NetworkResult<CelebritiesResponse>> = _popular

    init {
        viewModelScope.launch {
            celebritiesRepository.getTrendingCelebs().collect {
                _trending.value = it
            }
            celebritiesRepository.getPopularCelebs().collect {
                _popular.value = it
            }
        }
    }

}