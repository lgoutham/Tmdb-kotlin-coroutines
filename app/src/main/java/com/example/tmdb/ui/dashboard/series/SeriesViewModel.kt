package com.example.tmdb.ui.dashboard.series

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.repository.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val tvSeriesRepository: TvSeriesRepository) :
    BaseViewModel() {

    val airingTodaySeries = tvSeriesRepository.airingToday
    val onAirSeries = tvSeriesRepository.onAir
    val popularSeries = tvSeriesRepository.popular
    val topRatedSeries = tvSeriesRepository.topRated

    init {
        viewModelScope.launch {
            tvSeriesRepository.apply {
                getAiringTodaySeries()
                getOnAirSeries()
                getPopularSeries()
                getTopRatedSeries()
            }
        }
    }

}