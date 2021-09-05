package com.example.android_kotlin_demo.usecase

import com.example.android_kotlin_demo.domain.UserDataSource
import com.example.android_kotlin_demo.domain.UserDetails


class UserDetailsUseCase(
    private val dataSource: UserDataSource
) {
    suspend operator fun invoke(id: Int): UserDetails =
        dataSource.getUserInfo(id)

}