package com.synavos.androidassignment.data.service

import com.synavos.androidassignment.data.model.response.age.AgeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface APIService {


    @GET("/")
    suspend fun getAgeFromApi(
        @Query("name") name: String,
    ): Response<AgeResponse?>

}