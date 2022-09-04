package com.example.tmdb.ui.dashboard.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.TvSeriesRepository
import com.example.tmdb.response.tvseries.TvSeriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val tvSeriesRepository: TvSeriesRepository) :
    BaseViewModel() {

    private var _airingToday: MutableLiveData<NetworkResult<TvSeriesResponse>> = MutableLiveData()
    val airingTodaySeries: LiveData<NetworkResult<TvSeriesResponse>> = _airingToday
    private var _onAir: MutableLiveData<NetworkResult<TvSeriesResponse>> = MutableLiveData()
    val onAirSeries: LiveData<NetworkResult<TvSeriesResponse>> = _onAir
    private var _popular: MutableLiveData<NetworkResult<TvSeriesResponse>> = MutableLiveData()
    val popularSeries: LiveData<NetworkResult<TvSeriesResponse>> = _popular
    private var _topRated: MutableLiveData<NetworkResult<TvSeriesResponse>> = MutableLiveData()
    val topRatedSeries: LiveData<NetworkResult<TvSeriesResponse>> = _topRated

    init {
        viewModelScope.launch {
            _airingToday.postValue(tvSeriesRepository.getAiringTodaySeries())
            _onAir.postValue(tvSeriesRepository.getOnAirSeries())
            _popular.postValue(tvSeriesRepository.getPopularSeries())
            _topRated.postValue(tvSeriesRepository.getTopRatedSeries())
        }
    }

}