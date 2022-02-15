package com.bsuesi.testforwork.data

import com.bsuesi.testforwork.data.model.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val newsService: NewsService) {
    suspend fun getNews(): News {
        return newsService.getNews()
    }
}