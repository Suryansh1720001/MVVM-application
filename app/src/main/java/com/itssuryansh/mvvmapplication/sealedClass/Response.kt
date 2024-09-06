package com.itssuryansh.mvvmapplication.sealedClass

sealed class Response<T>(
    val data: T? = null, val errorMessage: String? = null, val failureMessage: String? = null
) {
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Loading<T>() : Response<T>()
    class Error<T>(errorMessage: String?) : Response<T>(errorMessage = errorMessage)
    class Failure<T>(failureMessage: String?) : Response<T>(failureMessage = failureMessage)
}