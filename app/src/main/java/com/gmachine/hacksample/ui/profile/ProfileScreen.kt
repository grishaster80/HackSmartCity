package com.gmachine.hacksample.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gmachine.hacksample.data.SignInInfo
import com.gmachine.hacksample.ui.profile.ProfileModel
import com.gmachine.hacksample.ui.profile.ProfileViewModel
import com.gmachine.hacksample.ui.theme.*

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    profileModel: ProfileModel,
    navHostController: NavHostController
) {
    val myProfileModel by viewModel.profileModel.observeAsState()
    if (viewModel.isUserAuthorized()) {
        ProfileScreenAuthorized(viewModel = viewModel, profileModel = myProfileModel)
    } else {
        ProfileScreenSignIn(viewModel, navHostController)
    }
    val signInResponse by viewModel.userSignInResponse.observeAsState()
    ProfileRouting(
        viewModel = viewModel,
        profileModel = myProfileModel,
        navHostController = navHostController,
        isUserAuthorized = viewModel.isUserAuthorized(),
        signInResponse = signInResponse
    )
}

@Composable
fun ProfileRouting(viewModel: ProfileViewModel,
                   profileModel: ProfileModel?,
                   navHostController: NavHostController,
                    isUserAuthorized: Boolean,
                    signInResponse: Boolean?) {
    if(signInResponse == true) {
        ProfileScreenAuthorized(viewModel = viewModel, profileModel = profileModel)
    } else if(isUserAuthorized) {
        ProfileScreenAuthorized(viewModel = viewModel, profileModel = profileModel)
    } else {
        ProfileScreenSignIn(viewModel, navHostController)
    }
}

@Composable
fun ProfileScreenSignIn(viewModel: ProfileViewModel, navHostController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.TopStart)) {
            Spacer(Modifier.height(40.dp))
            Surface(modifier = Modifier.fillMaxWidth()) {
                ScreenTitle(title = "Войдите в аккаунт")
            }
            Surface(modifier = Modifier.fillMaxWidth()) {
                ScreenDescription(description = "Войдите или зарегистрируйтесь, чтобы участвовать в соревнованиях")
            }
            Spacer(modifier = Modifier.height(60.dp))
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
            Spacer(modifier = Modifier.height(24.dp))
            Surface(modifier = Modifier.fillMaxWidth()) {
                DefaultWideButton(
                    title = "Войти",
                    buttonActiveColor = Purple500,
                    buttonTextStyle = ButtonTextStyleWhite
                ) {
                    viewModel.signIn(getSignInInfo(login, password))
                    Log.e("@@@", "Clicked")
                }
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            DefaultWideButton(
                title = "Создать аккаунт",
                buttonActiveColor = ColorShapeRegisterButton,
                buttonTextStyle = MaterialTheme.typography.h5
            ) {
                navHostController.navigate("create_account")
                Log.e("@@@", "Clicked")
            }
        }
    }
}

private fun getSignInInfo(login: String, password: String): SignInInfo {
    return SignInInfo(username = login, password = password)
}

@Composable
fun ProfileScreenAuthorized(
    viewModel: ProfileViewModel,
    profileModel: ProfileModel?
) {
    val myModel = profileModel ?: ProfileModel(fullName = "", login = "")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        AsyncImage(
            model = "https://cdn-icons-png.flaticon.com/512/1222/1222738.png",
            contentDescription = null,
            modifier = Modifier
                .size(94.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = myModel.fullName, style = ProfileNameTextStyle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = myModel.login, style = ProfileLoginTextStyle)
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ProfileCountElement(myModel.userTeams.size, "Команд")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ProfileCountElement(myModel.matches, "Матчей")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        ProfileVictoriesCountElement(myModel.wins)
        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Любимые виды спорта", style = MaterialTheme.typography.h5)
            Text(
                text = "Изменить",
                style = ProfileChangeSportsTextStyle,
                modifier = Modifier.clickable {
                    Log.e("@@@", "clcik")
                })
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(myModel.favoriteSports) { index, result ->
                FavoriteSportElement(result)
            }
        }
    }
}


@Composable
fun ProfileCountElement(count: Int, title: String) {
    Box(
        modifier = Modifier
            .height(84.dp)
            .fillMaxWidth()
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = count.toString(), style = MaterialTheme.typography.h3)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = title, style = ProfileCountElementCaptionTextStyle)
        }
    }
}

@Composable
fun ProfileVictoriesCountElement(count: Int) {
    Box(
        modifier = Modifier
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = com.gmachine.hacksample.R.drawable.cup_icon),
            contentDescription = null,
            modifier = Modifier.align(
                Alignment.CenterStart
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 56.dp)
        ) {
            Text(text = "У вас пока 5 побед", style = MaterialTheme.typography.button)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Посмотрите рейтинг команд", style = ProfileCountElementCaptionTextStyle)
        }
    }
}

@Composable
fun FavoriteSportElement(title: String) {
    Box(
        modifier = Modifier
            .background(color = ColorShapeGray, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 10.dp, horizontal = 16.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.body1)
    }
}