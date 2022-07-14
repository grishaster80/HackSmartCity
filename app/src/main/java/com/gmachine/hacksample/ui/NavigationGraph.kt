package com.gmachine.hacksample.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gmachine.hacksample.R
import com.gmachine.hacksample.data.Team
import com.gmachine.hacksample.ui.feed.*
import com.gmachine.hacksample.ui.onboarding.OnboardingModel
import com.gmachine.hacksample.ui.onboarding.OnboardingScreen
import com.gmachine.hacksample.ui.profile.ProfileModel
import com.gmachine.hacksample.utils.BottomNavItem

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "onboarding?screenNumber={screenNumber}") {
        composable(BottomNavItem.FEED.route) {
            FeedScreen(hiltViewModel(), FeedModel(listOfStories(), listOfMatchResult(), listOfNewsInfo()), navController)
        }
        composable(BottomNavItem.EVENTS.route) {
            EventsScreen(hiltViewModel())
        }
        composable(BottomNavItem.PROFILE.route) {
            ProfileScreen(hiltViewModel(), profileModel())
        }
        composable(route = "onboarding?screenNumber={screenNumber}", arguments = listOf(navArgument("screenNumber"){
            type = NavType.IntType
            defaultValue = 0
        })) {
            OnboardingScreen(onboardingModel(), navController, it.arguments?.getInt("screenNumber"))
        }

        composable(route = "story?id={id}", arguments = listOf(navArgument("id"){
            type = NavType.IntType
            defaultValue = 0
        })) {
            StoriesScreen(navController)
        }

    }
}

private fun listOfStories(): List<Story>{
    return listOf(Story("Событие или новость", R.drawable.sand),Story("Событие или новость", R.drawable.sand), Story("Событие или новость", R.drawable.sand), Story("Событие или новость", R.drawable.sand))
}

private fun listOfMatchResult(): List<com.gmachine.hacksample.ui.feed.MatchResult> {
    return listOf(MatchResult("Баскет", "Результаты", TeamResultInfo(R.drawable.sunset, "Милан", 10),TeamResultInfo(R.drawable.sand, "Интер", 4)),MatchResult("Баскет", "Результаты", TeamResultInfo(R.drawable.sunset, "Милан", 10),TeamResultInfo(R.drawable.sand, "Интер", 4)))
}

private fun listOfNewsInfo(): List<NewsInfo> {
    return listOf(NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.LIVE_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS),NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS))
}


private fun onboardingModel(): OnboardingModel {
    return OnboardingModel(R.drawable.sunset, "«Умный город: Живи спортом»", "Включи уведомления, я тебе напомню про таймер и когда начать детокс", false)
}

private fun profileModel(): ProfileModel{
    return ProfileModel("https://cdn-icons-png.flaticon.com/512/1222/1222738.png", "Дмитрий Новиков", "novaslide", userTeams = listOf(
        Team("a"), Team("b")
    ), 7, 5, listOf("Футбол", "Баскетбол", "Лыжи", "Плавание", "Воллейбол")
    )
}

val ROUTES = listOf<String>("feed", "events", "profile")