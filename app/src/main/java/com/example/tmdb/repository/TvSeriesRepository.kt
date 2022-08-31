package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.network.TvSeriesService
import com.example.tmdb.response.tvseries.TvSeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val tvSeriesService: TvSeriesService) {

    private var _airingToday: MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val airingToday: LiveData<TvSeriesResponse>
        get() = _airingToday
    private var _onAir: MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val onAir: LiveData<TvSeriesResponse>
        get() = _onAir
    private var _popular: MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val popular: LiveData<TvSeriesResponse>
        get() = _popular
    private var _topRated: MutableLiveData<TvSeriesResponse> = MutableLiveData()
    val topRated: LiveData<TvSeriesResponse>
        get() = _topRated

    suspend fun getAiringTodaySeries() {
        withContext(Dispatchers.IO) {
            val response = tvSeriesService.getAiringTodaySeries()
            if (response.isSuccessful) {
                _airingToday.postValue(response.body())
            }
        }
    }

    suspend fun getOnAirSeries() {
        withContext(Dispatchers.IO) {
            val response = tvSeriesService.getOnAirSeries()
            if (response.isSuccessful) {
                _onAir.postValue(response.body())
            }
        }
    }

    suspend fun getPopularSeries() {
        withContext(Dispatchers.IO) {
            val response = tvSeriesService.getPopularSeries()
            if (response.isSuccessful) {
                _popular.postValue(response.body())
            }
        }
    }

    suspend fun getTopRatedSeries() {
        withContext(Dispatchers.IO) {
            val response = tvSeriesService.getTopRatedSeries()
            if (response.isSuccessful) {
                _topRated.postValue(response.body())
            }
        }
    }

}