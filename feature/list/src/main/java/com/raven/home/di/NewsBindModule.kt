package com.raven.home.di

import com.raven.home.data.NewsRepository
import com.raven.repository.NewsLocalRepository
import com.raven.home.domain.NewsDataSource
import com.raven.database.NewsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsBindModule {

    @Binds
    abstract fun bindHiringSource(repository: NewsRepository): NewsDataSource

    @Binds
    abstract fun bindHiringLocalSource(localRepository: NewsLocalRepository) : NewsLocalDataSource
}
