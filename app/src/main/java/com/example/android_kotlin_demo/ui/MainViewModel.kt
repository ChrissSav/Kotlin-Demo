package com.example.android_kotlin_demo.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_kotlin_demo.data.base.BaseViewModel
import com.example.android_kotlin_demo.domain.UserDetails

class MainViewModel
@ViewModelInject
constructor() : BaseViewModel() {


    private val _userDetails = MutableLiveData<UserDetails?>()
    val userDetails: LiveData<UserDetails?> = _userDetails


    fun settUserDetails(userDetails: UserDetails) {
        launch {
            _userDetails.value = userDetails
        }
    }


}