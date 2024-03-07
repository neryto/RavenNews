package com.raven.home.data

import com.raven.home.data.remote.interactor.NetworkInteractor
import com.raven.home.data.remote.response.NewsResponse
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.data.remote.service.NewsService
import com.raven.home.domain.NewsDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val networkInteractor: NetworkInteractor,
    retrofit: Retrofit
    ) : NewsDataSource {

    private val newsService = retrofit.create(NewsService::class.java)

    override suspend fun getNews(): Flow<NetworkResult<NewsResponse>>  =
        networkInteractor.handleApi {
            newsService.getNews()
        }


}
