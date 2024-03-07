package com.raven.home.domain

import com.raven.home.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun getNews(): List<NewsEntity>?
    suspend fun saveNews(news:List<NewsEntity>)

}