package com.raven.home.domain

sealed class DomainResult<out T> {
    data class Success<out T>(val data: T) : DomainResult<T>()
    class Error<out T>(val code: Int? = null, val message: String?) : DomainResult<T>()
}