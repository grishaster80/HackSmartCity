package com.example.hacksample.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hacksample.utils.BottomNavItem

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.FEED.route) {
        composable(BottomNavItem.FEED.route) {
            FeedScreen(hiltViewModel())
        }
        composable(BottomNavItem.EVENTS.route) {
            EventsScreen(hiltViewModel())
        }
        composable(BottomNavItem.PROFILE.route) {
            ProfileScreen(hiltViewModel())
        }
    }
}