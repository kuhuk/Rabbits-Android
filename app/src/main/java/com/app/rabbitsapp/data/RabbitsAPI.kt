package com.app.rabbitsapp.data

import retrofit2.http.GET

interface RabbitsAPI {

    @GET("/random-rabbit")
    suspend fun getRandomRabbit(): Rabbit

    companion object {
        const val baseURL = "http://192.168.29.199:8080"
    }
}