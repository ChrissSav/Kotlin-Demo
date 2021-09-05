package com.example.android_kotlin_demo.ui.fragments


import com.example.android_kotlin_demo.data.base.BaseFragment
import com.example.android_kotlin_demo.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override fun getViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUpObservers() {
    }

    override fun setUpViews() {
    }
}