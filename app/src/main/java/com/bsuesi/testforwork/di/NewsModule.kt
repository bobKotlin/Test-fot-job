package com.bsuesi.testforwork.di

import com.bsuesi.testforwork.data.NewsService
import com.bsuesi.testforwork.data.Repository
import com.bsuesi.testforwork.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideMainService(retrofit : Retrofit) : NewsService = retrofit.create(NewsService::class.java)

    @Provides
    @Singleton
    fun provideMainRemoteData(newsService: NewsService ) : Repository = Repository(newsService)
}