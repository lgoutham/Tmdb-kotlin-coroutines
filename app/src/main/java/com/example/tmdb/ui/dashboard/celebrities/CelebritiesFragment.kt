package com.example.tmdb.ui.dashboard.celebrities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentCelebritiesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CelebritiesFragment :
    BaseFragment<FragmentCelebritiesBinding, CelebritiesViewModel>(FragmentCelebritiesBinding::inflate) {

    override fun initUI(savedInstanceState: Bundle?) {

    }

    override fun getViewModelObject(): CelebritiesViewModel {
        return ViewModelProvider(this)[CelebritiesViewModel::class.java]
    }

}