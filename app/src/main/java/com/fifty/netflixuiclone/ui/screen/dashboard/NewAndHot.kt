package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
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
import com.fifty.netflixuiclone.util.UiConstants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun NewAndHotScreen() {
    Column() {
        TopBar(stringResource(R.string.new_and_hot))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            val tabItems = listOf(
                stringResource(R.string.coming_soon),
                stringResource(R.string.everyones_watching)
            )
            val tabItemsIcons = listOf("\uD83D\uDC40", "\uD83C\uDF7F")
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()

            Surface {
                Column(modifier = Modifier.background(Color.Black)) {
                    TabRow(selectedTabIndex = pagerState.currentPage,
                        backgroundColor = Color.Transparent,
                        modifier = Modifier
                            .padding(end = 20.dp, start = 20.dp, bottom = 16.dp)
                            .clip(RoundedCornerShape(30.dp)),
                        indicator = {}) {
                        tabItems.forEachIndexed { index, title ->
                            val color = remember {
                                Animatable(Color.White)
                            }
                            LaunchedEffect(pagerState.currentPage == index) {
                                color.animateTo(if (pagerState.currentPage == index) Color.White else Color.Transparent)
                            }
                            Tab(selected = pagerState.currentPage == index,
                                modifier = Modifier
                                    .background(
                                        color = color.value, shape = RoundedCornerShape(30.dp)
                                    )
                                    .height(50.dp),
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = tabItemsIcons[index],
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp,
                                            modifier = Modifier.padding(end = 8.dp)
                                        )
                                        Text(
                                            text = title,
                                            style = if (pagerState.currentPage == index)
                                                TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            else TextStyle(
                                                color = Color.White,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            ), textAlign = TextAlign.Left
                                        )
                                    }
                                },
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                })
                        }
                    }

                    HorizontalPager(
                        count = tabItems.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.Top
                    ) { page ->
                        when (page) {
                            0 -> ComingSoonMoviesList()
                            1 -> EveryoneWatchingMoviesList()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(title:String) {
    Column {
        // Hiding App bar with netflix logo.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .animateContentSize(animationSpec = tween(durationMillis = PRIMARY_APPBAR_SCROLL_ANIMATION_DURATION))
                .height(PRIMARY_TOP_BAR_HEIGHT)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = title,
                        fontFamily = UiConstants.latoFontFamily,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
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
    }
}

@Composable
fun ComingSoonMoviesList() {
    val comingSoonMovies = listOf(1..10)
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items = (1..10).toList()) {
            ComingSoonCardItem()
        }
    }
}

@Composable
fun EveryoneWatchingMoviesList() {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items = (1..10).toList()) {
            EveryoneWatchingCardItem()
        }
    }
}

@Composable
fun ComingSoonCardItem() {
    Row(
        modifier = Modifier
            .wrapContentHeight(unbounded = true)
            .fillMaxWidth()
    ) {
        // Date Container vertical.
        Box(
            modifier = Modifier
                .width(70.dp)
                .fillMaxHeight()
        ) {
            Column() {
                Text(
                    text = "Jun",
                    style = TextStyle(
                        fontFamily = UiConstants.latoFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                )
                Text(
                    text = "10",
                    style = TextStyle(
                        fontFamily = UiConstants.latoFontFamily,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            // Coming soon movie image and volume button container.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                // Movie image.
                Image(
                    painter = painterResource(id = R.drawable.interstellar_movie_vertical),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Volume button.
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    androidx.compose.material3.Button(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {},
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.transparent_black),
                            contentColor = Color.White
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_volume_up),
                            contentDescription = "Volume Up",
                            alignment = Alignment.BottomEnd,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }
            // Movie Name and buttons.
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(7f)
                ) {
                    Text(
                        text = "Avatar: The Way of Water",
                        style = TextStyle(
                            fontFamily = UiConstants.latoFontFamily,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
                Row {
                    NewsAndHorVerticalContentButton(
                        "Remind Me",
                        painterResource(id = R.drawable.ic_round_notifications),
                        "Remind Me"
                    )
                    NewsAndHorVerticalContentButton(
                        "Help",
                        painterResource(id = R.drawable.ic_outline_info),
                        "Help"
                    )
                }
            }
            // Coming soon date.
            Text(
                text = "Coming on 14 Dec",
                style = TextStyle(fontFamily = UiConstants.latoFontFamily, color = Color.White)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.avatar_desc),
                style = TextStyle(
                    fontFamily = UiConstants.latoFontFamily,
                    color = colorResource(id = R.color.transparent_white)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun EveryoneWatchingCardItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp, top = 0.dp)
    ) {
        // Movie title.
        Text(
            text = "Avatar: The Way of Water",
            style = TextStyle(
                fontFamily = UiConstants.latoFontFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.avatar_desc),
            style = TextStyle(
                fontFamily = UiConstants.latoFontFamily,
                color = colorResource(id = R.color.transparent_white)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Movie image.
        Image(
            painter = painterResource(id = R.drawable.interstellar_movie_vertical),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
        )
        // Horizontal buttons.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            NewsAndHorVerticalContentButton(
                "Share",
                painterResource(id = R.drawable.ic_round_send),
                "Share"
            )
            NewsAndHorVerticalContentButton(
                "Add",
                painterResource(id = R.drawable.ic_round_add),
                "Add"
            )
            NewsAndHorVerticalContentButton(
                "Play",
                painterResource(id = R.drawable.ic_round_play_arrow),
                "Play"
            )
        }
    }
}

@Composable
fun NewsAndHorVerticalContentButton(
    buttonText: String,
    painter: Painter,
    contentDescription: String
) {
    androidx.compose.material3.Button(
        onClick = {}, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RectangleShape
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(Color.White)
            )
            androidx.compose.material3.Text(text = buttonText)
        }
    }
}


@ExperimentalPagerApi
@Composable
@Preview
fun NewAndHotScreenPreview() {
    NewAndHotScreen()
}