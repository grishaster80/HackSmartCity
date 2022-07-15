package com.gmachine.hacksample.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gmachine.hacksample.ui.DefaultBackButton
import com.gmachine.hacksample.ui.DefaultWideButton
import com.gmachine.hacksample.ui.theme.*
import com.gmachine.hacksample.utils.BottomNavItem

@Composable
fun OnboardingScreen(
    onboardingModel: OnboardingModel,
    navHostController: NavHostController,
    onBoardingScreenNumber: Int?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = com.gmachine.hacksample.R.drawable.ill),
                contentDescription = null,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(120.dp))
            Text(
                text = "«Умный город: Живи спортом»",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Это Всероссийские спортивные игры, по различным видам спорта",
                style = OnboardingSubtitleTextStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
        Surface(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 4.dp)) {
            DefaultWideButton(
                title = "Далее",
                buttonActiveColor = Purple500,
                buttonTextStyle = ButtonTextStyleWhite
            ) {
                navHostController.navigate(BottomNavItem.FEED.route) {
                    popUpTo(0)
                }
            }
        }
    }
}