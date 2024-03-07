package com.raven.home.data.remote.interactor

import com.raven.home.data.remote.response.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

class NetworkInteractor {
    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): Flow<NetworkResult<T>> = flow {
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(  NetworkResult.Success(body))
            } else {
                emit(NetworkResult.Error(
                    code = response.code(),
                    message = response.message()
                ))

            }
        } catch (e: HttpException) {
            emit(NetworkResult.Error(code = e.code(), message = e.message()))
        } catch (e: Throwable) {
            emit(NetworkResult.Exception(e))
        }
    }
}