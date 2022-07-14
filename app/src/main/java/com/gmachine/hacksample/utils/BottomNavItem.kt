package com.gmachine.hacksample.utils

import androidx.annotation.DrawableRes
import com.gmachine.hacksample.R

enum class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
) {
    FEED("Лента", R.drawable.feed_icon, "feed"),
    EVENTS("Соревнования", R.drawable.competitions_icon, "events"),
    PROFILE("Профиль", R.drawable.profile_icon, "profile"),

}