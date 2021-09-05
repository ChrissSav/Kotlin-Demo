package com.example.android_kotlin_demo.framework.dto

import com.google.gson.annotations.SerializedName


data class RegisterUserResponse(
    val id: Int,
    val email :String,
    @SerializedName("first_name")
    val firstName : String,
    @SerializedName("last_name")
    val lastName : String,
    @SerializedName("avatar")
    val picture : String
)
