package com.gmachine.hacksample.ui.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import com.gmachine.hacksample.ui.theme.ButtonTextStyleWhite
import com.gmachine.hacksample.ui.theme.ColorShapeVioletActive
import com.gmachine.hacksample.ui.theme.ColorShapeVioletNotActive
import com.gmachine.hacksample.ui.theme.OnboardingSubtitleTextStyle
import com.gmachine.hacksample.utils.BottomNavItem

@Composable
fun OnboardingScreen(
    onboardingModel: OnboardingModel,
    navHostController: NavHostController,
    onBoardingScreenNumber: Int?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (onboardingModel.isBackDisplayed) DefaultBackButton {
            navHostController.navigateUp()
        } else {
            Spacer(modifier = Modifier.height(40.dp))
        }
        Spacer(modifier = Modifier.height(58.dp))
        Image(
            painterResource(id = com.gmachine.hacksample.R.drawable.sunset),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(130.dp))
        Text(
            text = "«Умный город: Живи спортом»",
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Включи уведомления, я тебе напомню про таймер и когда начать детокс",
            style = OnboardingSubtitleTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        DefaultWideButton(
            title = "Далее",
            isButtonActive = true,
            buttonActiveColor = ColorShapeVioletActive,
            buttonNotActiveColor = ColorShapeVioletNotActive,
            buttonTextStyle = ButtonTextStyleWhite
        ) {
            navHostController.navigate(BottomNavItem.FEED.route){
                popUpTo(0)
            }
        }
    }
}