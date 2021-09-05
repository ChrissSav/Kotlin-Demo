package com.example.android_kotlin_demo.data

import com.example.android_kotlin_demo.domain.UserDataSource
import com.example.android_kotlin_demo.domain.mappers.mapToUser
import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest

class UserDataSourceImpl(
    private val repository: UserRepository
) : UserDataSource {

    override suspend fun register(registerUserRequest: RegisterUserRequest) = repository.register(registerUserRequest)

    override suspend fun getUserInfo(id: Int) = repository.getUserInfo(id).data.mapToUser()

}