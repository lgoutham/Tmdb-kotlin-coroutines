package com.example.tmdb.ui.dashboard.series

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.TvSeriesRepository
import com.example.tmdb.response.tvseries.TvSeriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val tvSeriesRepository: TvSeriesRepository) :
    BaseViewModel() {

    private var _airingToday: MutableStateFlow<NetworkResult<TvSeriesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val airingTodaySeries: StateFlow<NetworkResult<TvSeriesResponse>> = _airingToday
    private var _onAir: MutableStateFlow<NetworkResult<TvSeriesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val onAirSeries: StateFlow<NetworkResult<TvSeriesResponse>> = _onAir
    private var _popular: MutableStateFlow<NetworkResult<TvSeriesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val popularSeries: StateFlow<NetworkResult<TvSeriesResponse>> = _popular
    private var _topRated: MutableStateFlow<NetworkResult<TvSeriesResponse>> =
        MutableStateFlow(NetworkResult.Empty)
    val topRatedSeries: StateFlow<NetworkResult<TvSeriesResponse>> = _topRated

    init {
        viewModelScope.launch {
            tvSeriesRepository.getAiringTodaySeries().collect {
                _airingToday.value = it
            }
            tvSeriesRepository.getOnAirSeries().collect {
                _onAir.value = it
            }
            tvSeriesRepository.getPopularSeries().collect {
                _popular.value = it
            }
            tvSeriesRepository.getTopRatedSeries().collect {
                _topRated.value = it
            }
        }
    }

}