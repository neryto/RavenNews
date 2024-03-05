package com.raven.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    @Named("apiKeyInterceptorClient")
    fun providerApiKeyInterceptorClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(ApiKeyInterceptor())
        }.build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("apiKeyInterceptorClient") apiKeyInterceptorClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .client(apiKeyInterceptorClient)
            .build()
    }

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/"
    }
}
