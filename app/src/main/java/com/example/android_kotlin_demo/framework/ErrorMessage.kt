package com.example.android_kotlin_demo.framework

import com.example.android_kotlin_demo.R


data class ErrorMessage(
    val internal: Boolean = false,
    val messageId: Int = R.string.ERROR_SOMETHING_WRONG,
    val message: String = ""
)

fun internalError(messageId: Int): ErrorMessage {
    return ErrorMessage(internal = true, messageId = messageId)
}

fun externalError(message: String): ErrorMessage {
    return ErrorMessage(internal = false, message = message)
}