package com.gmachine.hacksample.di

import android.content.Context
import coil.ImageLoader
import com.gmachine.hacksample.network.MainService
import com.gmachine.hacksample.utils.network.BaseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(BaseInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context, okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context).okHttpClient { okHttpClient }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "http://35.173.193.253"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCompetitionsService(
        retrofit: Retrofit
    ): MainService {
        return retrofit.create(MainService::class.java)
    }

}