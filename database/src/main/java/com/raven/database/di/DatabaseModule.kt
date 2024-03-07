package com.raven.database.di

import android.content.Context
import androidx.room.Room
import com.raven.database.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun databaseProvider(@ApplicationContext context: Context): NewsDataBase = Room.databaseBuilder(
        context,
        NewsDataBase::class.java,
        "news_database"
    ).build()
}