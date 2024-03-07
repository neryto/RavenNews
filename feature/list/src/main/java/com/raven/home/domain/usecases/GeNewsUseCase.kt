package com.raven.home.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.home.data.remote.response.NetworkResult
import com.raven.home.domain.DomainResult
import com.raven.home.domain.NewsDataSource
import com.raven.database.NewsLocalDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.domain.NewsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeNewsUseCase @Inject constructor(
    private val dataSource: NewsDataSource,
    private val localSource: NewsLocalDataSource,
    private val mapper: GetNewsMapper
) : BaseUseCase<Unit, DomainResult<NewsData>>() {
    override suspend fun execute(params: Unit): Flow<DomainResult<NewsData>> = flow {
        dataSource.getNews().collect { newsResult ->
            when (newsResult) {
                is NetworkResult.Success -> {
                    //store news into db
                    localSource.saveNews(mapper.transformNetworkToDb(newsResult.data.results))
                    emit(mapper.transformNetworkToUI(newsResult))
                }
                else -> {
                    //an error was happen check if there are data into db
                    localSource.getNews()?.let { notNullNews ->
                        if (notNullNews.isNotEmpty())
                        //emit data from db
                            emit(mapper.transformDbToUI(notNullNews))
                        else
                            emit(mapper.transformNetworkToUI(newsResult))
                    } ?: run {
                        //emit the error
                        emit(mapper.transformNetworkToUI(newsResult))
                    }
                }
            }
        }
    }
}
