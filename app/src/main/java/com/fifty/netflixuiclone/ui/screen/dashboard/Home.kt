package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fifty.netflixuiclone.R
import com.fifty.netflixuiclone.util.UiConstants

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    val dashboardContentScrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        HomeScreenContent(dashboardContentScrollState)
        TopBar(dashboardContentScrollState)
    }
}

@Composable
fun TopBar(dashboardContentScrollState: ScrollState) {
    Column {
        // Hiding App bar with netflix logo.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .animateContentSize(animationSpec = tween(durationMillis = PRIMARY_APPBAR_SCROLL_ANIMATION_DURATION))
                .height(height = if (dashboardContentScrollState.isScrolled) 0.dp else PRIMARY_TOP_BAR_HEIGHT)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.netflix_logo),
                        contentDescription = "Netflix Logo"
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        // Screencast button.
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_screen_cast),
                                colorFilter = ColorFilter.tint(Color.White),
                                contentDescription = "Screencast"
                            )
                        }
                        // Profile Avatar.
                        IconButton(modifier = Modifier.padding(PaddingValues(10.dp)),
                            onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_img),
                                contentDescription = "Profile"
                            )
                        }
                    }
                }
            }
        }
        // Non hiding app bar with lists.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (dashboardContentScrollState.isScrolled) colorResource(id = R.color.transparent_black)
                    else Color.Transparent
                )
                .height(SECONDARY_TOP_BAR_HEIGHT)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                TopAppBarListButton(stringResource(R.string.tv_shows))
                TopAppBarListButton(stringResource(R.string.movies))
                TopAppBarListButton(stringResource(R.string.categories))
            }
        }
    }
}

@Composable
fun RowScope.TopAppBarListButton(buttonText: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.White
        )
    ) {
        Text(
            text = buttonText,
            fontSize = 14.sp,
            fontFamily = UiConstants.latoFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun HomeScreenContent(dashboardContentScrollState: ScrollState) {
    Column(Modifier.verticalScroll(dashboardContentScrollState)) {
        BannerImage()
        CommonMoviesLazyList(stringResource(R.string.released_in_the_past_year))
        CommonMoviesLazyList(stringResource(R.string.trending_now))
        NumberedMoviesLazyList(stringResource(R.string.top_ten_tv_show))
        CommonMoviesLazyList(stringResource(R.string.tense_dramas))
        CommonMoviesLazyList(stringResource(R.string.south_indian_cinema))
    }
}

@Composable
fun BannerImage() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        // Banner Image.
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.interstellar_movie_vertical),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        // Banner black gradient top and bottom.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to Color.Black, 0.5f to Color.Transparent, 0.99f to Color.Black
                    )
                )
        )
        // Banner buttons.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var painter = painterResource(id = R.drawable.ic_round_add)
                // My list button.
                BannerVerticalContentButton(
                    stringResource(id = R.string.my_list),
                    painter = painter,
                    contentDescription = stringResource(R.string.my_list)
                )
                // Play button.
                painter = painterResource(id = R.drawable.ic_round_play_arrow)
                BannerHorizontalContentButton(
                    stringResource(id = R.string.play),
                    painter = painter,
                    contentDescription = stringResource(R.string.play)
                )
                // Info button.
                painter = painterResource(id = R.drawable.ic_outline_info)
                BannerVerticalContentButton(
                    stringResource(id = R.string.info),
                    painter = painter,
                    contentDescription = stringResource(R.string.info)
                )
            }
        }
    }
}

@Composable
fun CommonMoviesLazyList(title: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            fontFamily = UiConstants.latoFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(items = (1..10).toList()) {
                CommonMovieCard(painter = painterResource(id = R.drawable.interstellar_movie_vertical))
            }
        }
    }
}

@Composable
fun NumberedMoviesLazyList(title: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            fontFamily = UiConstants.latoFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        val numbers = (1..10).toList()
        LazyRow {
            items(numbers) { number ->
                NumberedMovieCard(
                    painter = painterResource(id = R.drawable.interstellar_movie_vertical),
                    number = number
                )
            }
        }
    }
}

@Composable
fun CommonMovieCard(painter: Painter) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .height(200.dp)
            .width(140.dp)
            .padding(end = 8.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            painter = painter, contentDescription = "", contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun NumberedMovieCard(painter: Painter, number: Int) {
    Card(
        elevation = 0.dp, modifier = Modifier
            .height(200.dp)
            .width(180.dp)
            .padding(end = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.background(Color.Black)
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(140.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = number.toString(),
                modifier = Modifier
                    .align(Alignment.BottomStart),
                fontSize = 100.sp,
                fontFamily = UiConstants.latoFontFamily, fontWeight = FontWeight.Black,
                color = Color.White,
            )
        }
    }
}

@Composable
fun BannerVerticalContentButton(buttonText: String, painter: Painter, contentDescription: String) {
    Button(
        onClick = {}, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.White
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(text = buttonText)
        }
    }
}

@Composable
fun BannerHorizontalContentButton(
    buttonText: String, painter: Painter, contentDescription: String
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 16.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(Color.Black)
        )
        Text(
            text = buttonText, fontFamily = UiConstants.latoFontFamily, fontWeight = FontWeight.Bold
        )
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}


