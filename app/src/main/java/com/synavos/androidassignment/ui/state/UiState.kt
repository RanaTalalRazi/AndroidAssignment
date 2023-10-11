package com.synavos.androidassignment.ui.state

import com.synavos.androidassignment.data.model.RequestException


sealed class UiState {
    object LOADING : UiState()
    object NORMAL : UiState()
    object OFFLINE : UiState()
    open class SUCCESS<out E>(val data: E? = null) : UiState()
    open class ERROR(val error: String? = null) : UiState()

}