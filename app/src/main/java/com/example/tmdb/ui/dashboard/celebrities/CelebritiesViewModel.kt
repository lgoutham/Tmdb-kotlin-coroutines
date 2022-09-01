package com.example.tmdb.ui.dashboard.celebrities

import androidx.lifecycle.viewModelScope
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.repository.CelebritiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebritiesViewModel @Inject constructor(private val celebritiesRepository: CelebritiesRepository) :
    BaseViewModel() {

    val trending = celebritiesRepository.trending
    val popular = celebritiesRepository.popular

    init {
        viewModelScope.launch {
            celebritiesRepository.apply {
                getTrendingCelebs()
                getPopularCelebs()
            }
        }
    }

}