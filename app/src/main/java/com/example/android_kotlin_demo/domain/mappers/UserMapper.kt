package com.example.android_kotlin_demo.domain.mappers

import com.example.android_kotlin_demo.domain.UserDetails
import com.example.android_kotlin_demo.framework.dto.RegisterUserResponse

fun RegisterUserResponse.mapToUser() =
    UserDetails(id, email, firstName, lastName, picture)