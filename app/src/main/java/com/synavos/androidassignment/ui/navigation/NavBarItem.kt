package com.synavos.androidassignment.ui.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.List
import com.synavos.androidassignment.R

object NavBarItems {

    fun getBarItems(contextApp: Context): List<BarItem> {
        return listOf(
            BarItem(
                title = contextApp.getString(R.string.home),
                image = Icons.Filled.Home,
                route = "home"
            ),
            BarItem(
                title = contextApp.getString(R.string.history),
                image = Icons.Filled.List,
                route = "history"
            ),
            BarItem(
                title = contextApp.getString(R.string.result),
                image = Icons.Filled.Info,
                route = "result"
            )
        )
    }


}