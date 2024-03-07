package com.raven.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raven.database.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity")
    fun getAll() : List<NewsEntity>?

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    fun getNew(id:String) : NewsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)
}