package com.example.android_kotlin_demo.framework

import java.io.IOException


class BaseApiException(val stringCode: Int? = null, val text: String? = null) : Exception()

class NoInternetException : IOException()
