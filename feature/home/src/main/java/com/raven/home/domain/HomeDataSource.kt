package com.raven.home.domain

import com.raven.home.data.remote.response.HomeResponse
import com.raven.home.data.remote.response.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    suspend fun getNews(): Flow<NetworkResult<HomeResponse>>
}
