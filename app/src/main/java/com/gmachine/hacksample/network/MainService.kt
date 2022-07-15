package com.gmachine.hacksample.network

import com.gmachine.hacksample.data.*
import retrofit2.http.*

interface MainService {
    @GET("/api/feed/competitions")
    suspend fun getFoodCategories(): CompetitionsResponse

    @POST("/api/user")
    suspend fun signUp(@Body signUpInfo: SignUpInfo): UserSignUpResponse


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/auth/realms/Test/protocol/openid-connect/token")
    suspend fun signIn(@Body signInInfo: SignInInfo): SignInResponse

    @GET("/api/user")
    suspend fun getUser(@Header("Authorization") token: String): UserResponse

    @GET("/api/feed/news")
    suspend fun getNews(): FeedNewsResponse

}