package com.synavos.androidassignment.ui.navigation

sealed class NavRoutes(val route: String) {
    object MainScreen : NavRoutes("mainScreen")
    object Home : NavRoutes("home")
    object History : NavRoutes("history")
    object Result : NavRoutes("result")

}