package com.gmachine.hacksample.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gmachine.hacksample.ui.profile.ProfileModel
import com.gmachine.hacksample.ui.profile.ProfileViewModel
import com.gmachine.hacksample.ui.theme.*

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    profileModel: ProfileModel
) {
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
        Text(text = profileModel.fullName, style = ProfileNameTextStyle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = profileModel.login, style = ProfileLoginTextStyle)
        Spacer(modifier = Modifier.height(32.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                ProfileCountElement(profileModel.userTeams.size, "Команд")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                ProfileCountElement(profileModel.matches, "Матчей")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        ProfileVictoriesCountElement(profileModel.wins)
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
            itemsIndexed(profileModel.favoriteSports) { index, result ->
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