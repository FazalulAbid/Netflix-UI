package com.fifty.netflixuiclone.ui.screen.dashboard.downloads

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fifty.netflixuiclone.R
import com.fifty.netflixuiclone.ui.screen.dashboard.TopBar
import com.fifty.netflixuiclone.ui.screen.welcome.FullWidthButton
import com.fifty.netflixuiclone.util.UiConstants

@ExperimentalMaterialApi
@Composable
fun DownloadsScreen() {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val downloadsScrollState = rememberScrollState()
        TopBar(stringResource(R.string.downloads))
        Column(
            Modifier
                .fillMaxSize()
        ) {
            // Smart Downloads button.
            SmartDownloadsButton()
            // Introducing text.
            Text(
                text = "Introducing downloads for you",
                style = TextStyle(
                    fontFamily = UiConstants.latoFontFamily,
                    fontSize = 26.sp,
                    color = Color.White, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(horizontal = 24.dp)
            )
            // Downloads Description.
            Text(
                text = stringResource(R.string.downloads_description),
                style = TextStyle(
                    fontFamily = UiConstants.latoFontFamily,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_gray),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                ), modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )
            // Three Cards.
            DownloadsThreeCards()
            // Buttons.
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                // Set up blue button
                FullWidthButton(
                    buttonText = "Set Up",
                    contentColor = Color.White,
                    containerColor = Color.Blue
                ) {
                    // Button Onclick
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Set up White Button
                FullWidthButton(
                    buttonText = "See what you can download",
                    contentColor = Color.Black,
                    containerColor = Color.White
                ) {
                    // Button Onclick
                }
            }
        }
    }
}

@Composable
fun DownloadsThreeCards() {
    Surface(
        color = Color.Black, modifier = Modifier.height(320.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(x = (-80).dp, y = 30.dp)
                .rotate(-20f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.prestige_poster),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .height(200.dp)
                    .width(140.dp),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .offset(x = 80.dp, y = 30.dp)
                .rotate(20f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.interstellar_movie_vertical),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .height(200.dp)
                    .width(140.dp),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ironman_poster),
                contentDescription = "",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .height(200.dp)
                    .width(140.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SmartDownloadsButton() {
    Button(
        onClick = {},
        Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RectangleShape, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.White
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Smart Downloads settings icon.
            Image(
                painter = painterResource(id = R.drawable.ic_round_settings_24),
                contentDescription = "Smart Downloads",
                colorFilter = ColorFilter.tint(color = Color.White)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Smart Downloads button text.
            Text(
                text = stringResource(R.string.smart_downloads),
                style = TextStyle(color = Color.White, fontFamily = UiConstants.latoFontFamily)
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun DownloadsPreview() {
    DownloadsScreen()
}