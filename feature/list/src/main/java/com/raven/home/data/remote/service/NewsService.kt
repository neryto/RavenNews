package com.raven.home.data.remote.service

import com.raven.home.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
    @GET("mostpopular/v2/emailed/{period}.json")
    suspend fun getNews(@Path("period") accountId : Int = 7): Response<NewsResponse>
}
