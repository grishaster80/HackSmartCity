package com.example.hacksample.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hacksample.R
import com.example.hacksample.ui.feed.FeedModel
import com.example.hacksample.ui.feed.MatchResult
import com.example.hacksample.ui.feed.Story
import com.example.hacksample.ui.feed.TeamResultInfo
import com.example.hacksample.utils.BottomNavItem

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.FEED.route) {
        composable(BottomNavItem.FEED.route) {
            FeedScreen(hiltViewModel(), FeedModel(listOfStories(), listOfMatchResult()))
        }
        composable(BottomNavItem.EVENTS.route) {
            EventsScreen(hiltViewModel())
        }
        composable(BottomNavItem.PROFILE.route) {
            ProfileScreen(hiltViewModel())
        }
    }
}

private fun listOfStories(): List<Story>{
    return listOf(Story("Событие или новость", R.drawable.sand),Story("Событие или новость", R.drawable.sand), Story("Событие или новость", R.drawable.sand), Story("Событие или новость", R.drawable.sand))
}

private fun listOfMatchResult(): List<com.example.hacksample.ui.feed.MatchResult> {
    return listOf(MatchResult("Баскет", "Результаты", TeamResultInfo(R.drawable.sunset, "Милан", 10),TeamResultInfo(R.drawable.sand, "Интер", 4)),MatchResult("Баскет", "Результаты", TeamResultInfo(R.drawable.sunset, "Милан", 10),TeamResultInfo(R.drawable.sand, "Интер", 4)))
}