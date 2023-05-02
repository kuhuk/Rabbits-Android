package com.app.rabbitsapp.di

import com.app.rabbitsapp.data.RabbitsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRabbitAPI(): RabbitsAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RabbitsAPI.baseURL)
            .build()
            .create(RabbitsAPI::class.java)
    }
}