package com.example.hacksample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hacksample.ui.feed.FeedModel
import com.example.hacksample.ui.feed.FeedViewModel
import com.example.hacksample.ui.feed.Story
import com.example.hacksample.ui.feed.TeamResultInfo
import com.example.hacksample.ui.theme.ColorShapeGray
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    feedModel: FeedModel
) {
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = false), onRefresh = { }) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            item {
                ScreenTitle(title = "Лента")
            }
            item {
                StoryItems(storiesList = feedModel.stroriesList)
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ScreenSubTitle(title = "Результаты матчей")
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                MatchResultItems(matchResultList = feedModel.matchResultList)
            }

        }
    }
}

@Composable
fun StoryItems(
    storiesList: List<Story>
) {
    Spacer(Modifier.height(20.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(storiesList) { index, story ->
            StoryItem(storyItem = story)
        }
    }
}

@Composable
fun StoryItem(
    storyItem: Story
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painterResource(id = storyItem.imageSrc),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = storyItem.title, style = MaterialTheme.typography.h6, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@Composable
fun MatchResultItems(
    matchResultList: List<com.example.hacksample.ui.feed.MatchResult>
) {
    Spacer(Modifier.height(12.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(matchResultList) { index, result ->
            MatchResultItem(matchResult = result)
        }
    }
}

@Composable
fun MatchResultItem(
    matchResult: com.example.hacksample.ui.feed.MatchResult
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.dp))
            .width(300.dp)
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = matchResult.title,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(
                text = matchResult.subTitle,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        TeamResultRow(teamResultInfo = matchResult.team0Result)
        Spacer(modifier = Modifier.height(16.dp))
        TeamResultRow(teamResultInfo = matchResult.team1Result)
    }
}

@Composable
fun TeamResultRow(teamResultInfo: TeamResultInfo) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.CenterStart)) {
            Image(
                painterResource(id = teamResultInfo.teamLogo),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Text(text = teamResultInfo.teamName, style = MaterialTheme.typography.overline, modifier = Modifier.align(Alignment.CenterVertically))
        }
        Text(
            text = teamResultInfo.teamScore.toString(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

