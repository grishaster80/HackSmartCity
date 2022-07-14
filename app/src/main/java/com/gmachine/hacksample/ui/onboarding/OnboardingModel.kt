package com.gmachine.hacksample.ui.onboarding

import androidx.annotation.DrawableRes

data class OnboardingModel(@DrawableRes val imageRes: Int, val title: String, val subTitle: String, val isBackDisplayed: Boolean)