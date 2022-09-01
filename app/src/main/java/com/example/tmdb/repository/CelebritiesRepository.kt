package com.example.tmdb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb.network.CelebritiesService
import com.example.tmdb.response.celebrities.CelebritiesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CelebritiesRepository @Inject constructor(private val celebritiesService: CelebritiesService) {

    private var _trending: MutableLiveData<CelebritiesResponse> = MutableLiveData()
    val trending: LiveData<CelebritiesResponse>
        get() = _trending

    private var _popular: MutableLiveData<CelebritiesResponse> = MutableLiveData()
    val popular: LiveData<CelebritiesResponse>
        get() = _popular

    suspend fun getTrendingCelebs() {
        withContext(Dispatchers.IO) {
            val response = celebritiesService.getTrending()
            if (response.isSuccessful) {
                _trending.postValue(response.body())
            }
        }
    }

    suspend fun getPopularCelebs() {
        withContext(Dispatchers.IO) {
            val response = celebritiesService.getPopular()
            if (response.isSuccessful) {
                _popular.postValue(response.body())
            }
        }
    }
}