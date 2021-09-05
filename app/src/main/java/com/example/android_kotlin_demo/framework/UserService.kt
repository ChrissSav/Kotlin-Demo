package com.example.android_kotlin_demo.framework

import com.example.android_kotlin_demo.framework.dto.RegisterUserDataResponse
import com.example.android_kotlin_demo.framework.dto.RegisterUserRequest
import com.example.android_kotlin_demo.framework.dto.RegisterUserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("register")
    suspend fun register(
        @Body registerUserRequest: RegisterUserRequest
    ): Response<RegisterUserTokenResponse>

    @GET("users/{user_id}")
    suspend fun getUserById(
        @Path ("user_id") userId :Int
    ): Response<RegisterUserDataResponse>
}