package com.synavos.androidassignment.feature.home

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
class HomeViewModel @Inject constructor(
    private val useCase: AgeUsecase, @ApplicationContext application: Context
) : BaseViewModel(application) {

    fun getAge(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user=useCase.checkUserExist(name)
            if (user==null){
                getAgeFromApi(name)
            }else{
                _uiState.emit(UiState.SUCCESS(user))
            }
        }
    }
    fun getAgeFromApi(name: String) {
        _uiState.tryEmit(UiState.LOADING)
        call({
            useCase.getAgeFromApi(name)
        }, onSuccess = { response ->
            viewModelScope.launch(Dispatchers.IO) {
                if (response != null) useCase.adduser(response)

                _uiState.emit(UiState.SUCCESS(response))
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