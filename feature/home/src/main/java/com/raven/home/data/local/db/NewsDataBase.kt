package com.raven.home.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raven.home.data.local.dao.NewsDao
import com.raven.home.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}