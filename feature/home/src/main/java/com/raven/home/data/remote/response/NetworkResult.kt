package com.raven.home.data.remote.response

sealed class NetworkResult<out T>{
    data class Success<out T>(val data: T) : NetworkResult<T>()
    class Error<out T>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<out T>(val e: Throwable) : NetworkResult<T>()
}