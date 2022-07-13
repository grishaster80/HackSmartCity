package com.example.hacksample.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hacksample.AppState
import com.example.hacksample.utils.BottomNavItem
import com.example.hacksample.utils.navigation.NavScreen

@Composable
fun HackMainScreen() {
    val navController = rememberNavController()
    val appState = AppState()
    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                BottomNavigation(backgroundColor = Color.White, elevation = 0.dp) {
                    val currentRoute = navController.currentDestination?.route
                    val items = listOf<BottomNavItem>(
                        BottomNavItem.FEED,
                        BottomNavItem.EVENTS,
                        BottomNavItem.PROFILE
                    )

                    items.forEach { item ->
                        BottomNavigationItem(selected = currentRoute == item.route, onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }, icon = { Icon(imageVector = item.icon, null, tint = Color.Black) })
                    }
                }
            }
        }
    ) {
        NavigationGraph(navController = navController)
    }
}