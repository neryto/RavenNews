package com.raven.home.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.home.domain.DomainResult
import com.raven.home.domain.HomeDataSource
import com.raven.home.domain.mapper.GetNewsMapper
import com.raven.home.presentation.NewsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeNewsUseCase @Inject constructor(
    private val dataSource: HomeDataSource,
    private val mapper: GetNewsMapper
) : BaseUseCase<Unit, DomainResult<NewsData>>() {
    override suspend fun execute(params: Unit): Flow<DomainResult<NewsData>> = flow {
        dataSource.getNews().collect {
           emit( mapper.transformDomainToUI(it))
        }
    }
}
