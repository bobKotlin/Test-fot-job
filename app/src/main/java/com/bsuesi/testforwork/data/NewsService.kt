package com.bsuesi.testforwork.data

import com.bsuesi.testforwork.data.model.News
import com.bsuesi.testforwork.utils.Constants.API_KEY
import retrofit2.http.GET

interface NewsService {
    @GET("v2/top-headlines?country=ua&apiKey=$API_KEY")
    suspend fun getNews(): News
}