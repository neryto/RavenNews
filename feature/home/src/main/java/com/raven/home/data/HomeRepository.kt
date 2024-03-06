package com.raven.home.data

import com.raven.home.data.remote.interactor.NetworkInteractor
import com.raven.home.data.remote.response.HomeResponse
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.data.remote.service.HomeService
import com.raven.home.domain.HomeDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val networkInteractor: NetworkInteractor,
    retrofit: Retrofit
    ) : HomeDataSource {

    private val newsService = retrofit.create(HomeService::class.java)

    override suspend fun getNews(): Flow<NetworkResult<HomeResponse>>  =
        networkInteractor.handleApi {
            newsService.getNews()
        }


}
