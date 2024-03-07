package com.raven.home.di

import com.raven.home.data.remote.interactor.NetworkInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {

    @Provides
    @Singleton
    fun networkInteractor(): NetworkInteractor =
        NetworkInteractor()

}