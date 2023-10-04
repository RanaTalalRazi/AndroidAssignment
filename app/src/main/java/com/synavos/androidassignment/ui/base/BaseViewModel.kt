package com.synavos.androidassignment.ui.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.synavos.androidassignment.ui.state.UiState
import com.synavos.androidassignment.util.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel(@ApplicationContext application: Context) : LifecycleObserver,
    AndroidViewModel(application as Application) {

    protected val _uiState = MutableStateFlow<UiState?>(UiState.NORMAL)
    val uiState = _uiState.asStateFlow()

    fun <T> call(
        apiCall: suspend () -> Result<T>,
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
    ) = viewModelScope.launch {
        try {
            if (!NetworkUtil.isInternetAvailable(getApplication<Application>().applicationContext)) {
                _uiState.tryEmit(UiState.OFFLINE)
                return@launch
            }
            val result = apiCall.invoke()

            // Check for result
            result.getOrNull()?.let { value ->
                onSuccess?.invoke(value)
            }

            result.exceptionOrNull()?.let { error ->
                onError?.invoke(error)
            }
        } catch (ex: Throwable) {
            onError?.invoke(ex)
        }
    }

    fun updateState(state: UiState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

}