package com.example.android_kotlin_demo.domain

import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest
import com.example.android_kotlin_demo.framework.dto.RegisterUserTokenResponse

interface UserDataSource {

    // Auth
    suspend fun register(registerUserRequest: RegisterUserRequest): RegisterUserTokenResponse

    //Info
    suspend fun getUserInfo(id: Int): UserDetails
}