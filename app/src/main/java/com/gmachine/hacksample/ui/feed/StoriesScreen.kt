package com.gmachine.hacksample.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.gmachine.hacksample.R
import com.ui.simplestories.Stories

@Composable
fun StoriesScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        val listOfImages = listOf<Int>(R.drawable.story_1, R.drawable.story_2)
        Stories(numberOfPages = listOfImages.size, slideDurationInSeconds = 2L, touchToPause = false, onComplete = {navHostController.navigateUp()}) {
            Image(painter = painterResource(id = listOfImages[it]), contentDescription = null,
                contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        }
    }
}