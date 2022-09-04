package com.example.tmdb.ui.details

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.base.BaseFragment
import com.example.tmdb.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(FragmentMovieDetailsBinding::inflate) {

    private var movieId: Int = -1

    companion object {
        fun newInstance(id: Int) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(DetailsActivity.MEDIA_ID, id)
            }
        }
    }

    override fun initUI(savedInstanceState: Bundle?) {
        arguments?.let {
            movieId = it.getInt(DetailsActivity.MEDIA_ID)
        }
        binding.id.text = movieId.toString()
    }

    override fun getViewModelObject(): MovieDetailsViewModel {
        return ViewModelProvider(this)[MovieDetailsViewModel::class.java]
    }

}