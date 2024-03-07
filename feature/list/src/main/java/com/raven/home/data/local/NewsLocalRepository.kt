package com.raven.home.data.local

import com.raven.home.data.local.db.NewsDataBase
import com.raven.home.data.local.entity.NewsEntity
import com.raven.home.domain.NewsLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalRepository
@Inject
constructor(private val database: NewsDataBase) : NewsLocalDataSource {
    override suspend fun getNews(): List<NewsEntity>? =
        withContext(Dispatchers.IO) {
            database.newsDao().getAll()
        }


    override suspend fun saveNews(news: List<NewsEntity>) {
        withContext(Dispatchers.IO) {
            database.newsDao().insertAll(news)
        }
    }

}