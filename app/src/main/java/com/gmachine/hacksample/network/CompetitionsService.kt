package com.gmachine.hacksample.network

import com.gmachine.hacksample.data.CompetitionsResponse
import retrofit2.http.GET

interface CompetitionsService {
    @GET("/api/feed/competitions")
    suspend fun getFoodCategories(): CompetitionsResponse
}