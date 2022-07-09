package com.example.hacksample.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val title: String,
                         val icon: ImageVector,
                         val route: String) {
    FEED("FEED", Icons.Outlined.Home, "feed"),
    EVENTS("EVENTS", Icons.Outlined.ShoppingCart, "events"),
    PROFILE("PROFILE", Icons.Outlined.Person, "profile"),

}