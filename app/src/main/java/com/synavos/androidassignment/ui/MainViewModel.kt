package com.synavos.androidassignment.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.synavos.androidassignment.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext application: Context
) : BaseViewModel(application) {
    var userInput=MutableLiveData<String>("")
}