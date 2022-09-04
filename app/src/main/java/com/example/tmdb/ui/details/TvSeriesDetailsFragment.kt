package com.example.tmdb.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentTvSeriesDetailsBinding

class TvSeriesDetailFragment :
    BaseFragment<FragmentTvSeriesDetailsBinding, TvSeriesDetailsViewModel>(
        FragmentTvSeriesDetailsBinding::inflate
    ) {

    private var seriesId: Int = -1

    companion object {
        fun newInstance(id: Int) = TvSeriesDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(DetailsActivity.MEDIA_ID, id)
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        arguments?.let {
            seriesId = it.getInt(DetailsActivity.MEDIA_ID)
        }
        binding.id.text = seriesId.toString()
    }

    override fun getViewModelObject(): TvSeriesDetailsViewModel {
        return ViewModelProvider(this)[TvSeriesDetailsViewModel::class.java]
    }
}