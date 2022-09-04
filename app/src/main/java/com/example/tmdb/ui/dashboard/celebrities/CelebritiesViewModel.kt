package com.example.tmdb.ui.dashboard.celebrities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.network.NetworkResult
import com.example.tmdb.repository.CelebritiesRepository
import com.example.tmdb.response.celebrities.CelebritiesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel @Inject constructor(private val celebritiesRepository: CelebritiesRepository) :
    BaseViewModel() {

    private var _trending: MutableLiveData<NetworkResult<CelebritiesResponse>> = MutableLiveData()
    val trending: LiveData<NetworkResult<CelebritiesResponse>> = _trending
    private var _popular: MutableLiveData<NetworkResult<CelebritiesResponse>> = MutableLiveData()
    val popular: LiveData<NetworkResult<CelebritiesResponse>> = _popular

    init {
        viewModelScope.launch {
            _trending.postValue(celebritiesRepository.getTrendingCelebs())
            _popular.postValue(celebritiesRepository.getPopularCelebs())
        }
    }

}