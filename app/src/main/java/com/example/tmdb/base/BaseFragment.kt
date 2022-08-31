package com.example.tmdb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    protected lateinit var model: VM
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = getViewModelObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(savedInstanceState)
    }

    abstract fun initUI(savedInstanceState: Bundle?)

    abstract fun getViewModelObject(): VM

    protected fun showProgressBar() {

    }

    protected fun hideProgressBar() {

    }

    protected fun replaceFragment(
        transaction: FragmentManager,
        container: Int,
        fragment: Fragment
    ) {
        transaction.beginTransaction().replace(container, fragment).commit()
    }

}