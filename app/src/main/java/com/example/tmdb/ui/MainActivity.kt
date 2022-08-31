package com.example.tmdb.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.tmdb.R
import com.example.tmdb.base.BaseActivity
import com.example.tmdb.base.BaseViewModel
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.ui.dashboard.celebrities.CelebritiesFragment
import com.example.tmdb.ui.dashboard.movies.MoviesFragment
import com.example.tmdb.ui.dashboard.search.SearchFragment
import com.example.tmdb.ui.dashboard.series.SeriesFragment
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, BaseViewModel>(ActivityMainBinding::inflate),
    NavigationBarView.OnItemSelectedListener {

    private var moviesFragment: MoviesFragment = MoviesFragment()
    private var seriesFragment: SeriesFragment = SeriesFragment()
    private var celebritiesFragment: CelebritiesFragment = CelebritiesFragment()
    private var searchFragment: SearchFragment = SearchFragment()
    private var activeFragment: Fragment = moviesFragment

    override fun initUI(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, seriesFragment)
            .hide(seriesFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, celebritiesFragment)
            .hide(celebritiesFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, searchFragment)
            .hide(searchFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, moviesFragment)
            .commit()
        binding.navView.setOnItemSelectedListener(this)
    }

    override fun getViewModelObject(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_movies -> {
                loadFragment(moviesFragment)
                return true
            }
            R.id.navigation_series -> {
                loadFragment(seriesFragment)
                return true
            }
            R.id.navigation_celebrities -> {
                loadFragment(celebritiesFragment)
                return true
            }
            R.id.navigation_search -> {
                loadFragment(searchFragment)
                return true
            }
        }
        return false
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

}

