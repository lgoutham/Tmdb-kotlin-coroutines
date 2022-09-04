package com.example.tmdb.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentCelebrityDetailsBinding

class CelebrityDetailsFragment :
    BaseFragment<FragmentCelebrityDetailsBinding, CelebrityDetailsViewModel>(
        FragmentCelebrityDetailsBinding::inflate
    ) {
    private var seriesId: Int = -1

    companion object {
        fun newInstance(id: Int) = CelebrityDetailsFragment().apply {
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

    override fun getViewModelObject(): CelebrityDetailsViewModel {
        return ViewModelProvider(this)[CelebrityDetailsViewModel::class.java]
    }
}