package com.gmachine.hacksample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.gmachine.hacksample.R
import com.gmachine.hacksample.ui.theme.ScreenDescriptionTextStyle

@Composable
fun ScreenTitle(title: String) {
    Spacer(Modifier.height(40.dp))
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

@Composable
fun ScreenSubTitle(title: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

@Composable
fun ScreenDescription(description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        Text(
            text = description,
            style = ScreenDescriptionTextStyle,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}

@Composable
fun TextFieldHint(hint: String) {
    Text(text = hint, style = ScreenDescriptionTextStyle)
}

@Composable
fun DefaultWideButton(
    title: String,
    buttonActiveColor: Color,
    buttonTextStyle: TextStyle,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonActiveColor, contentColor = buttonActiveColor),
        enabled = true,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = title, style = buttonTextStyle, modifier = Modifier.padding(10.dp).background(buttonActiveColor).align(Alignment.CenterVertically))
    }
}

@Composable
fun DefaultWideButton1(
    title: String,
    isButtonActive: Boolean,
    buttonActiveColor: Color,
    buttonNotActiveColor: Color,
    buttonTextStyle: TextStyle,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = if (isButtonActive) buttonActiveColor else buttonNotActiveColor),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(text = title, style = buttonTextStyle, modifier = Modifier.padding(12.dp))
    }
}

@Composable
fun DefaultBackButton(onButtonClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = onButtonClick, modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                null,
            )
        }
    }
}

@Composable
fun DefaultCloseButton(onButtonClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(onClick = onButtonClick, modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                null,
            )
        }
    }
}