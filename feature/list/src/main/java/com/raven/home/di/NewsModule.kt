package com.raven.home.di

import android.content.Context
import androidx.room.Room
import com.raven.home.data.local.db.NewsDataBase
import com.raven.home.data.remote.interactor.NetworkInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun networkInteractor(): NetworkInteractor =
        NetworkInteractor()

    @Provides
    @Singleton
    fun databaseProvider( @ApplicationContext context: Context) : NewsDataBase
            = Room.databaseBuilder(
        context,
        NewsDataBase::class.java,
        "news_database"
    ).build()


}