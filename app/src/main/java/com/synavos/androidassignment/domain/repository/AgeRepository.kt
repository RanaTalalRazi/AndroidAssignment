package com.synavos.androidassignment.domain.repository

import androidx.lifecycle.LiveData
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.network.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AgeRepository {

    suspend fun getAgeFromApi(name: String): Flow<DataState<AgeResponse>>

    suspend fun checkUserExist(name: String): AgeResponse?

    suspend fun addUser(user:AgeResponse)

    suspend fun getUserList(): List<AgeResponse>?




}