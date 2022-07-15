package com.gmachine.hacksample.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmachine.hacksample.ui.events.EventsViewModel
import com.gmachine.hacksample.ui.feed.NewsInfo
import com.gmachine.hacksample.ui.feed.NewsType

@Composable
fun EventsScreen(
    viewModel: EventsViewModel
){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            ScreenTitle(title = "Соревнования")
            Spacer(modifier = Modifier.height(48.dp))
        }
        item {
            repeat(10) {
                NewsInfoItem(newsInfo = NewsInfo("Заголовок с текстом события или новости", "Настольный теннис", "Дата/Время", "", NewsType.DEFAULT_NEWS))
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}