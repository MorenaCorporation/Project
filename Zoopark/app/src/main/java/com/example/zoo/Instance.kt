package com.plcoding.retrofitcrashcourse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {

    val api: API_Source by lazy {
        Retrofit.Builder()
            .baseUrl("https://zoo-animal-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API_Source::class.java)
    }
}