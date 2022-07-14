package com.gmachine.hacksample.utils.navigation

sealed class NavScreen(val route: String) {

    object Feed : NavScreen("Feed")
    object Events : NavScreen("Events")
    object Profile : NavScreen("Profile")
}
