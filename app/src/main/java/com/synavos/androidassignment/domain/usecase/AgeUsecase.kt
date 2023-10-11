package com.synavos.androidassignment.domain.usecase


import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.domain.repository.AgeRepository
import com.synavos.androidassignment.network.DataState
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class AgeUsecase @Inject constructor(private val repository: AgeRepository) {


    suspend fun getAgeFromApi(name: String): Flow<DataState<AgeResponse>> {
      return  repository.getAgeFromApi(name)
    }


    suspend fun checkUserExist(name: String): AgeResponse? {
        return repository.checkUserExist(name)
    }

    suspend fun adduser(user: AgeResponse) {
        repository.addUser(user)
    }

    suspend fun getUserList(): List<AgeResponse>? {
        return repository.getUserList()
    }


}