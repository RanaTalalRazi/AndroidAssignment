package com.synavos.androidassignment.data.service

import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.network.adapter.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("/")
    suspend fun getAgeFromApi(
        @Query("name") name: String,
    ): ApiResponse<AgeResponse?>

}