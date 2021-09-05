package com.example.android_kotlin_demo.util

import com.example.android_kotlin_demo.R
import com.example.android_kotlin_demo.framework.BaseApiException
import com.example.android_kotlin_demo.framework.dto.ErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun <T : Any> networkCall(
    function: suspend () -> T
): T {
    return withContext(Dispatchers.IO) {
        try {
            function.invoke()
        } catch (exception: Exception) {
            throw exception
        }
    }
}


fun <T : Any> Response<T>.handleApiFormat(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw BaseApiException(text = getErrorResponseErrorBody(errorBody()))
    }
}


fun getErrorResponseErrorBody(errorBody: ResponseBody?): String {
    return try {
        if (errorBody == null)
            throw BaseApiException(stringCode = R.string.ERROR_SOMETHING_WRONG)
        val mJsonString = errorBody.string()
        val parser = JsonParser()
        val mJson = parser.parse(mJsonString)
        val errorResponseModel = Gson().fromJson(mJson, ErrorResponse::class.java)
        errorResponseModel.error
    } catch (e: Exception) {
        throw BaseApiException(stringCode = R.string.ERROR_SOMETHING_WRONG)
    }

}


