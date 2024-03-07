package com.raven.detail.domain.usecases

import com.raven.core.bases.BaseUseCase
import com.raven.database.NewsLocalDataSource
import com.raven.detail.domain.NewData
import com.raven.detail.domain.mapper.GetNewMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewUseCase @Inject constructor(
    private val localSource: NewsLocalDataSource,
    private val getNewMapper: GetNewMapper
    ) : BaseUseCase<String, NewData>() {
    override suspend fun execute(params: String): Flow<NewData> = flow {
        localSource.getNew(params)?.let { notNullNewData->
           emit(getNewMapper.transformDBToUI(notNullNewData))
        }
    }
}