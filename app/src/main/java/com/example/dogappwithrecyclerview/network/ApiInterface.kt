package com.example.dogappwithrecyclerview.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list/all")
    suspend fun getAllBreeds() : Response<AllBreedsList>

    @GET("breed/{name}/images")
    suspend fun getByBreeds(@Path("name") name:String) : Response<ByBreed>
}