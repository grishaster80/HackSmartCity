package com.gmachine.hacksample.ui.profile

import com.gmachine.hacksample.data.Team

data class ProfileModel(
    val avatarUrl: String = "https://cdn-icons-png.flaticon.com/512/1222/1222738.png",
    val fullName: String,
    val login: String,
    val userTeams: List<Team> = listOf(Team("Team"),Team("Team"),Team("Team"),Team("Team"),Team("Team")),
    val matches: Int = 7,
    val wins: Int = 5,
    val favoriteSports: List<String> = listOf("Футбол", "Баскетбол", "Лыжи", "Плавание", "Воллейбол")
)