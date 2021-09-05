package com.example.android_kotlin_demo.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_kotlin_demo.data.base.BaseViewModel
import com.example.android_kotlin_demo.domain.UserDetails
import com.example.android_kotlin_demo.usecase.RegisterUserUseCase
import com.example.android_kotlin_demo.usecase.UserDetailsUseCase

class RegisterViewModel
@ViewModelInject
constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUserDetailsUseCase: UserDetailsUseCase
) :  BaseViewModel() {


    private val _userDetails = MutableLiveData<UserDetails?>()
    val userDetails: LiveData<UserDetails?> = _userDetails

    fun registerUser(email: String, password: String) {
        launch {
            val res = registerUserUseCase(email, password)
            _userDetails.value = getUserDetailsUseCase(res.id)
        }
    }

}