package com.example.hacksample.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import com.example.hacksample.ui.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "PROFILE")
    }
}