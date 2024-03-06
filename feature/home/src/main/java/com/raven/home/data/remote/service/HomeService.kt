package com.raven.home.data.remote.service

import com.raven.home.data.remote.response.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("mostpopular/v2/emailed/{period}.json")
    suspend fun getNews(@Path("period") accountId : Int = 7): Response<HomeResponse>
}
