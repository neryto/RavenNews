package com.raven.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raven.home.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity")
    fun getAll() : List<NewsEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)
}