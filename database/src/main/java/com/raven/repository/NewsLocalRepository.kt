package com.raven.repository

import com.raven.database.NewsDataBase
import com.raven.database.entity.NewsEntity
import com.raven.database.NewsLocalDataSource
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

    override suspend fun getNew(id: String): NewsEntity?  =
        withContext(Dispatchers.IO){
            database.newsDao().getNew(id)
        }




    override suspend fun saveNews(news: List<NewsEntity>) {
        withContext(Dispatchers.IO) {
            database.newsDao().insertAll(news)
        }
    }

}