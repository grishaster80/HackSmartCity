package com.gmachine.hacksample.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.gmachine.hacksample.ui.events.EventsViewModel

@Composable
fun EventsScreen(
    viewModel: EventsViewModel
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "EVENTS")
    }
}