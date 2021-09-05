package com.example.android_kotlin_demo.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserDetails(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val picture: String
) : Parcelable