package com.example.hacksample.ui.feed

import androidx.annotation.DrawableRes

data class FeedModel (val stroriesList: List<Story>, val matchResultList: List<com.example.hacksample.ui.feed.MatchResult>)

data class Story(val title: String, @DrawableRes val imageSrc: Int)

data class MatchResult(val title: String, val subTitle: String, val team0Result: TeamResultInfo, val team1Result: TeamResultInfo)

data class TeamResultInfo(@DrawableRes val teamLogo: Int, val teamName: String, val teamScore: Int)