package com.synavos.androidassignment.util

import android.app.Application
import com.synavos.androidassignment.R
import javax.inject.Inject

class StringUtils @Inject constructor(private val appContext: Application) {
    fun noNetworkErrorMessage() = appContext.getString(R.string.no_internet)
    fun somethingWentWrong() = appContext.getString(R.string.something_went_wrong)
}