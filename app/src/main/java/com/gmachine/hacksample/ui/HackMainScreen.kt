package com.gmachine.hacksample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gmachine.hacksample.AppState
import com.gmachine.hacksample.rememberAppState
import com.gmachine.hacksample.ui.theme.ColorTextPrimary
import com.gmachine.hacksample.ui.theme.ColorTextSubTitle2
import com.gmachine.hacksample.utils.BottomNavItem

@Composable
fun HackMainScreen() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val isNeedToShowBottomBar = currentDestination?.route in BottomNavItem.values().map { it.route }
    val currentScreen =
        ROUTES.find { it == currentDestination?.route } ?: BottomNavItem.FEED.route
    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        bottomBar = {
            if (isNeedToShowBottomBar) {
                BottomNavigation(backgroundColor = Color.White, elevation = 0.dp) {
                    val items = listOf<BottomNavItem>(
                        BottomNavItem.FEED,
                        BottomNavItem.EVENTS,
                        BottomNavItem.PROFILE
                    )
                    items.forEach { item ->
                        val isSelected = currentScreen == item.route
                        BottomNavigationItem(selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    style = if (isSelected) MaterialTheme.typography.subtitle1 else MaterialTheme.typography.subtitle1
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = item.icon),
                                    null,
                                    tint = if (isSelected) ColorTextPrimary else ColorTextSubTitle2
                                )
                            })
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController, hiltViewModel())
        }
    }
}