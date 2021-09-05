package com.example.android_kotlin_demo.ui

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android_kotlin_demo.R
import com.example.android_kotlin_demo.data.base.BaseActivityViewModel
import com.example.android_kotlin_demo.databinding.ActivityMainBinding
import com.example.android_kotlin_demo.util.createAlerter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivityViewModel<ActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    private lateinit var navController: NavController

    override fun provideViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun setUpObservers() {

        viewModel.error.observe(this){
            if (it.internal)
                createAlerter(getString(it.messageId))
            else
                createAlerter(it.message)
        }

        viewModel.load.observe(this, {
            if (it)
                binding.genericLoader.root.visibility = View.VISIBLE
            else
                binding.genericLoader.root.visibility = View.INVISIBLE

        })
    }

    override fun setUpViews() {

        navController = findNavController(R.id.nav_host_fragment)

        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        binding.bottomNavView.let {
            NavigationUI.setupWithNavController(it, navController)
            it.setOnItemReselectedListener { item ->
                if (item.isChecked) {
                    return@setOnItemReselectedListener
                }
            }
        }

    }
}