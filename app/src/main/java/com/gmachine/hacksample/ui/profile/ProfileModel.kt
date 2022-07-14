package com.gmachine.hacksample.ui.profile

import com.gmachine.hacksample.data.Team

data class ProfileModel(
    val avatarUrl: String,
    val fullName: String,
    val login: String,
    val userTeams: List<Team>,
    val matches: Int,
    val wins: Int,
    val favoriteSports: List<String>
)