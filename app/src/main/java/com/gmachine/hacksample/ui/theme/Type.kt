package com.gmachine.hacksample.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gmachine.hacksample.R

// Set of Material typography styles to start with
val Manrope = FontFamily(
    Font(R.font.manrope_regular),
    Font(R.font.manrope_bold, FontWeight.Bold)
)


val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = ColorTextPrimary
    ),
    h2 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = ColorTextPrimary
    ),
    h3 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = ColorTextPrimary
    ),
    h4 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = ColorTextPrimary
    ),
    h5 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = ColorTextPrimary
    ),
    h6 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = ColorTextPrimary
    ),
    body1 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = ColorTextCaption
    ),
    overline = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = ColorTextPrimary
    ),
    button = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = ColorTextPrimary
    ),
    subtitle1 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = ColorTextPrimary
    ),
    subtitle2 = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = ColorTextSubTitle2
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val ButtonTextStyleWhite = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = White
)

val OnboardingSubtitleTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = ColorTextOnboardingSubtitle
)

val ProfileCountElementCaptionTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = ColorTextCaption
)

val ProfileNameTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = ColorTextPrimary
)

val ProfileLoginTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = ColorTextOnboardingSubtitle
)

val ProfileChangeSportsTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = ColorShapeVioletActive
)

val FeedLiveItemTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = Color.White
)

val ScreenDescriptionTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = ColorTextScreenDescription
)

val TextFieldTextStyle = TextStyle(
    fontFamily = Manrope,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    color = ColorTextField
)