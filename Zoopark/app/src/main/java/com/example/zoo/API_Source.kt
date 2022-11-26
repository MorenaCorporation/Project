package com.plcoding.retrofitcrashcourse

import retrofit2.Response
import retrofit2.http.GET

interface API_Source {

    @GET("/animals/rand/10")
    suspend fun getAnimals(): Response<List<Animal>>

}