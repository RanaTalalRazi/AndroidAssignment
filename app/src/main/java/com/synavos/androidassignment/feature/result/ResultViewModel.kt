package com.synavos.androidassignment.feature.result

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.synavos.androidassignment.data.model.RequestException
import com.synavos.androidassignment.domain.usecase.AgeUsecase
import com.synavos.androidassignment.ui.base.BaseViewModel
import com.synavos.androidassignment.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResultViewModel @Inject constructor(
    private val useCase: AgeUsecase, @ApplicationContext application: Context
) : BaseViewModel(application) {

    fun getHistList(name: String) {
        _uiState.tryEmit(UiState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            val userList = useCase.getUserList()
            if (userList.isNullOrEmpty() && name.isNotEmpty()) {
                getAgeFromApi(name)
            } else {
                _uiState.emit(UiState.SUCCESS(userList))
            }
        }

    }

    fun getAgeFromApi(name: String) {
        call({
            useCase.getAgeFromApi(name)
        }, onSuccess = { response ->
            viewModelScope.launch {
                _uiState.emit(UiState.SUCCESS(arrayListOf(response)))
            }
        }, onError = {
            viewModelScope.launch {
                val errorState = UiState.ERROR()
                errorState.error = (it as? RequestException)?.message.toString()
                _uiState.emit(errorState)
            }
        })
    }

}