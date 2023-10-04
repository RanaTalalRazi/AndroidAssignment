package com.synavos.androidassignment.domain.usecase

import com.google.gson.Gson
import com.synavos.androidassignment.data.model.RequestException
import com.synavos.androidassignment.data.model.response.ErrorResponse
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.domain.repository.AgeRepository
import javax.inject.Inject

class AgeUsecase @Inject constructor(private val repository: AgeRepository) {


    suspend fun getAgeFromApi(name: String): Result<AgeResponse?> {

        return try {
            val response = repository.getAgeFromApi(name)
            if (response.isSuccessful) {
                val data = response.body()
                Result.success(data)

            } else {
                val error = response.errorBody()!!.string()
                val errorMessage = Gson().fromJson(error, ErrorResponse::class.java).error

                Result.failure(RequestException(response.code(), errorMessage ?: ""))
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
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