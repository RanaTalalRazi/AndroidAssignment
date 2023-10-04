package com.synavos.androidassignment.data.repository

import androidx.lifecycle.LiveData
import com.synavos.androidassignment.data.AndroidAssignmentDatabase
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.data.service.APIService
import com.synavos.androidassignment.domain.repository.AgeRepository
import retrofit2.Response
import javax.inject.Inject

class AgeRepositoryImpl  @Inject constructor(
    private val apiService: APIService,
    private val db:AndroidAssignmentDatabase
):AgeRepository{
    override suspend fun getAgeFromApi(name: String): Response<AgeResponse?> = apiService.getAgeFromApi(name)

    override suspend fun checkUserExist(name: String): AgeResponse? {
        return db.eventsDao().checkUserExist(name)
    }

    override suspend fun addUser(user: AgeResponse) {
        db.eventsDao().addUser(user)
    }

    override suspend fun getUserList(): List<AgeResponse>? {
        return db.eventsDao().getUserList()

    }
}