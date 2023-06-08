package com.example.dogappwithrecyclerview.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClientRetrofit {

    private val BASE_URL = "https://dog.ceo/api/"

    val retrofit : Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

}