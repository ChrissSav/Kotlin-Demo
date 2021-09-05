package com.example.android_kotlin_demo.data

import com.example.android_kotlin_demo.framework.dto.RegisterUserDataResponse
import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest
import com.example.android_kotlin_demo.framework.dto.RegisterUserTokenResponse

interface  UserRepository {

    // Auth
    suspend fun register(registerUserRequest: RegisterUserRequest): RegisterUserTokenResponse

    //Info
    suspend fun getUserInfo(id: Int): RegisterUserDataResponse


}