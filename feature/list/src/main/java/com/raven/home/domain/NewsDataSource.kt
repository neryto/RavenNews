package com.raven.home.domain

import com.raven.home.data.remote.response.NewsResponse
import com.raven.home.data.remote.response.NetworkResult
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    suspend fun getNews(): Flow<NetworkResult<NewsResponse>>
}
