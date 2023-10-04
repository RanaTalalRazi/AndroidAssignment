package com.synavos.androidassignment.feature.history

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
class HistoryViewModel @Inject constructor(
    private val useCase: AgeUsecase,
    @ApplicationContext application: Context
) : BaseViewModel(application){

    fun getHistList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.SUCCESS(useCase.getUserList()))
        }

    }

}