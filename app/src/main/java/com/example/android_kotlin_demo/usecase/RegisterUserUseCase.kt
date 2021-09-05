package com.example.android_kotlin_demo.usecase

import com.example.android_kotlin_demo.domain.UserDataSource
import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest


class RegisterUserUseCase(
    private val dataSource: UserDataSource
) {
    suspend operator fun invoke(email: String, password: String) =
        dataSource.register(RegisterUserRequest(email, password))

}