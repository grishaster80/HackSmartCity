package com.example.hacksample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import com.example.hacksample.ui.feed.FeedViewModel

@Composable
fun FeedScreen(
    viewModel: FeedViewModel
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "FEED")
    }
}