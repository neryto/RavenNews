package com.raven.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raven.database.dao.NewsDao
import com.raven.database.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}