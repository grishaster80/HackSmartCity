package com.gmachine.hacksample.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gmachine.hacksample.ui.feed.*
import com.gmachine.hacksample.ui.theme.ColorLiveRed
import com.gmachine.hacksample.ui.theme.ColorShapeGray
import com.gmachine.hacksample.ui.theme.FeedLiveItemTextStyle


@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    feedModel: FeedModel,
    navHostController: NavHostController
) {
    val myFeedModel by viewModel.feedModel.observeAsState()
    MyInfo(viewModel, myFeedModel, navHostController)
}

@Composable
fun MyInfo(
    viewModel: FeedViewModel,
    feedModel: FeedModel?,
    navHostController: NavHostController
) {
    val myModel = feedModel ?: FeedModel(listOfStories(), listOfMatchResult(), listOfNewsInfo())
    if (feedModel == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
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
                StoryItems(storiesList = myModel.stroriesList, navHostController)
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ScreenSubTitle(title = "Результаты матчей")
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                MatchResultItems(matchResultList = myModel.matchResultList)
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ScreenSubTitle(title = "Новости")
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                myModel.newsInfoList.forEach {
                    if (it.newsType == NewsType.DEFAULT_NEWS) {
                        NewsInfoItem(newsInfo = it)
                    } else {
                        NewsInfoLiveItem(newsInfo = it)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }

}

@Composable
fun StoryItems(
    storiesList: List<Story>,
    navHostController: NavHostController
) {
    Spacer(Modifier.height(20.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(storiesList) { index, story ->
            StoryItem(storyItem = story, navHostController)
        }
    }
}

@Composable
fun StoryItem(
    storyItem: Story,
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navHostController.navigate("story")
            }
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
    matchResultList: List<com.gmachine.hacksample.ui.feed.MatchResult>
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
    matchResult: com.gmachine.hacksample.ui.feed.MatchResult
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
            Text(
                text = teamResultInfo.teamName,
                style = MaterialTheme.typography.overline,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Text(
            text = teamResultInfo.teamScore.toString(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun NewsInfoItem(newsInfo: NewsInfo) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
            .padding(end = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 96.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = newsInfo.title,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = newsInfo.subTitle,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = newsInfo.dateString,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/1222/1222738.png",
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .align(Alignment.CenterEnd),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun NewsInfoLiveItem(newsInfo: NewsInfo) {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
            .clickable {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
                context.startActivity(browserIntent)
            }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painterResource(id = com.gmachine.hacksample.R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopStart)
            ) {
                LiveItem()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = newsInfo.title,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Box(Modifier.fillMaxSize()) {
                    Text(
                        text = newsInfo.subTitle,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Text(
                        text = newsInfo.dateString,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Composable
fun LiveItem() {
    Box(
        modifier = Modifier
            .background(color = ColorLiveRed, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = "LIVE", style = FeedLiveItemTextStyle)
    }
}

