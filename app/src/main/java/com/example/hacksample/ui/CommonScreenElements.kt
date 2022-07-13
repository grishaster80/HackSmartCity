package com.example.hacksample.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenTitle(title: String) {
    Spacer(Modifier.height(40.dp))
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = title, style = MaterialTheme.typography.h3, modifier = Modifier.align(Alignment.CenterStart))
    }
}

@Composable
fun ScreenSubTitle(title: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = title, style = MaterialTheme.typography.h5, modifier = Modifier.align(Alignment.CenterStart))
    }
}