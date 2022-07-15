package com.gmachine.hacksample.ui.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gmachine.hacksample.data.Credential
import com.gmachine.hacksample.data.SignUpInfo
import com.gmachine.hacksample.ui.DefaultWideButton
import com.gmachine.hacksample.ui.TextFieldHint
import com.gmachine.hacksample.ui.theme.*

@Composable
fun CreateAccountScreen(profileViewModel: ProfileViewModel) {
    var name by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(68.dp))
            Image(
                painterResource(id = com.gmachine.hacksample.R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier
                    .size(94.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Изменить фото", style = TextFieldTextStyle)
            Spacer(modifier = Modifier.height(40.dp))
            TextField(
                value = name,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = ColorTextField,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = ColorShapeGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    name = it
                    Log.e("@@@", "Name is $it")
                },
                textStyle = TextFieldTextStyle,
                placeholder = { TextFieldHint("Введите имя") })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = login,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = ColorTextField,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = ColorShapeGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    login = it
                    Log.e("@@@", "Login is $it")
                },
                textStyle = TextFieldTextStyle,
                placeholder = { TextFieldHint("Введите логин") })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value = password,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    password = it
                    Log.e("@@@", "Pass is $it")
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = ColorTextField,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = ColorShapeGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextFieldTextStyle,
                placeholder = { TextFieldHint("Введите пароль") },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisibility) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
        }
        val context: Context = LocalContext.current
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            DefaultWideButton(
                title = "Зарегестрироваться",
                buttonActiveColor = Purple500,
                buttonTextStyle = ButtonTextStyleWhite
            ) {
                profileViewModel.signUp(getSignUpInfo(name, login, password))
                Toast.makeText(context, "Пользователь успешно зарегестрирован", Toast.LENGTH_SHORT).show()
                Log.e("@@@", "Clicked")
            }
        }
    }
}

private fun getSignUpInfo(name: String,login: String, password:String): SignUpInfo {
    return SignUpInfo(username = login, email = login +"@gmail.com", firstName = name, lastName = "", credentials = listOf(
        Credential(value = "password")
    ))
}