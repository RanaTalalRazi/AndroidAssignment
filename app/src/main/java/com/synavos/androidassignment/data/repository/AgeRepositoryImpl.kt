package com.synavos.androidassignment.data.repository

import androidx.lifecycle.LiveData
import com.synavos.androidassignment.data.AndroidAssignmentDatabase
import com.synavos.androidassignment.data.model.response.age.AgeResponse
import com.synavos.androidassignment.data.service.APIService
import com.synavos.androidassignment.domain.repository.AgeRepository
import com.synavos.androidassignment.network.DataState
import com.synavos.androidassignment.network.adapter.message
import com.synavos.androidassignment.network.adapter.onError
import com.synavos.androidassignment.network.adapter.onException
import com.synavos.androidassignment.network.adapter.onNetworkError
import com.synavos.androidassignment.network.adapter.onSuccess
import com.synavos.androidassignment.util.StringUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AgeRepositoryImpl  @Inject constructor(
    private val apiService: APIService,
    val stringUtils: StringUtils,
    private val db:AndroidAssignmentDatabase
):AgeRepository{
    override suspend fun getAgeFromApi(name: String): Flow<DataState<AgeResponse>>{
        return flow {
            apiService.getAgeFromApi(name)
                .onSuccess { data?.let { emit(DataState.success(it)) } }
                .onError { emit(DataState.error<AgeResponse>(message()?:stringUtils.somethingWentWrong())) }
                .onException { emit(DataState.error<AgeResponse>(stringUtils.somethingWentWrong())) }
                .onNetworkError { emit(DataState.error<AgeResponse>(stringUtils.noNetworkErrorMessage())) }
        }.flowOn(Dispatchers.IO)
    }

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