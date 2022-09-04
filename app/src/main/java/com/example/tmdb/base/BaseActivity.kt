package com.example.tmdb.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var model: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = getViewModelObject()
        binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        initUI(savedInstanceState)
    }

    abstract fun initUI(savedInstanceState: Bundle?)

    abstract fun getViewModelObject(): VM

    protected fun showProgressBar() {

    }

    protected fun hideProgressBar() {

    }

    protected fun replaceFragment(
        manager: FragmentManager,
        container: Int,
        fragment: Fragment?
    ) {
        if (fragment == null) return
        manager.beginTransaction().replace(container, fragment).commit()
    }

}