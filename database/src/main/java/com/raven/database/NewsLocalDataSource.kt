package com.raven.database

import com.raven.database.entity.NewsEntity

interface NewsLocalDataSource {
    suspend fun getNews(): List<NewsEntity>?

    suspend fun getNew(id:String) : NewsEntity?
    suspend fun saveNews(news:List<NewsEntity>)

}