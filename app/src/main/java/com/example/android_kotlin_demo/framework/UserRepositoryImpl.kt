package com.example.android_kotlin_demo.framework

import com.example.android_kotlin_demo.data.UserRepository
import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest
import com.example.android_kotlin_demo.util.handleApiFormat
import com.example.android_kotlin_demo.util.networkCall

class UserRepositoryImpl(
    private val service: UserService
) : UserRepository {

    override suspend fun register(registerUserRequest: RegisterUserRequest) =
        networkCall {
            service.register(registerUserRequest).handleApiFormat()
        }


    override suspend fun getUserInfo(id: Int) =
        networkCall {
            service.getUserById(id).handleApiFormat()
        }
}