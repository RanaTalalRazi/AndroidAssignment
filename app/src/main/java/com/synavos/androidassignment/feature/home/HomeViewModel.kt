package com.synavos.androidassignment.feature.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.synavos.androidassignment.data.model.RequestException
import com.synavos.androidassignment.domain.usecase.AgeUsecase
import com.synavos.androidassignment.network.DataState
import com.synavos.androidassignment.ui.base.BaseViewModel
import com.synavos.androidassignment.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: AgeUsecase, @ApplicationContext application: Context
) : BaseViewModel(application) {

    fun getAge(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = useCase.checkUserExist(name)
            if (user == null) {
                getAgeFromApi(name)
            } else {
                _uiState.emit(UiState.SUCCESS(user))
            }
        }
    }

    fun getAgeFromApi(name: String) {
        viewModelScope.launch{
            useCase.getAgeFromApi(name).onStart { _uiState.tryEmit(UiState.LOADING) }.collect{
                when(it){
                    is DataState.Success->{
                        _uiState.emit(UiState.SUCCESS(it.data))
                    }
                    is DataState.Error->{
                        _uiState.emit(UiState.ERROR(it.message))
                    }
                }
            }
        }

    }

}