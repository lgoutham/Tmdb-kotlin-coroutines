package com.example.tmdb.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tmdb.base.BaseActivity
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.databinding.ActivityDetailsBinding

class DetailsActivity :
    BaseActivity<ActivityDetailsBinding, BaseViewModel>(ActivityDetailsBinding::inflate) {

    private lateinit var mediaType: String
    private var mediaId: Int = -1

    companion object {
        fun newInstance(activity: Activity, mediaType: String, id: Int) =
            Intent(activity, DetailsActivity::class.java).apply {
                putExtra(MEDIA_TYPE, mediaType)
                putExtra(MEDIA_ID, id)
            }

        const val MEDIA_TYPE = "MEDIA_TYPE"
        const val MEDIA_ID = "MEDIA_ID"
    }

    override fun initUI(savedInstanceState: Bundle?) {
        intent?.let {
            mediaType = it.getStringExtra(MEDIA_TYPE).toString()
            mediaId = it.getIntExtra(MEDIA_ID, -1)
        }
        val fragment: Fragment? = when (mediaType) {
            "movie" ->
                MovieDetailsFragment.newInstance(mediaId)
            "tv" -> {
                TvSeriesDetailFragment.newInstance(mediaId)
            }
            "person" -> {
                CelebrityDetailsFragment.newInstance(mediaId)
            }
            else -> {
                null
            }
        }
        replaceFragment(supportFragmentManager, binding.container.id, fragment)
    }

    override fun getViewModelObject(): BaseViewModel {
        return BaseViewModel()
    }
}