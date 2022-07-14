package com.gmachine.hacksample.ui.feed

import androidx.annotation.DrawableRes

data class FeedModel(val stroriesList: List<Story>, val matchResultList: List<com.gmachine.hacksample.ui.feed.MatchResult>, val newsInfoList: List<NewsInfo>)

data class Story(val title: String, @DrawableRes val imageSrc: Int)

data class MatchResult(val title: String, val subTitle: String, val team0Result: TeamResultInfo, val team1Result: TeamResultInfo)

data class TeamResultInfo(@DrawableRes val teamLogo: Int, val teamName: String, val teamScore: Int)

data class NewsInfo(val title: String, val subTitle: String, val dateString: String, val imageUrl: String, val newsType: NewsType)

enum class NewsType{
    DEFAULT_NEWS, LIVE_NEWS
}