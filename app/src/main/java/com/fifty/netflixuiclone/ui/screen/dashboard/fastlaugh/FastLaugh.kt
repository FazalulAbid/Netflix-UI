package com.fifty.netflixuiclone.ui.screen.dashboard.fastlaugh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fifty.netflixuiclone.R
import com.fifty.netflixuiclone.ui.screen.dashboard.NewsAndHorVerticalContentButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.random.Random

@ExperimentalPagerApi
@Composable
fun FastLaughScreen() {
    val verticalPagerState = rememberPagerState()
    Surface(color = Color.Red) {
        VerticalPager(
            count = 10,
            state = verticalPagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            VerticalPagerReelContent()
        }

    }
}

@Composable
fun VerticalPagerReelContent() {
    val color = Color(
        Random.nextFloat(),
        Random.nextFloat(),
        Random.nextFloat(),
        1f
    )
    Box(modifier = Modifier.background(color)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
        ) {
            NewsAndHorVerticalContentButton(
                buttonText = "Lol",
                painter = painterResource(id = R.drawable.ic_round_happy),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            NewsAndHorVerticalContentButton(
                buttonText = "My List",
                painter = painterResource(id = R.drawable.ic_round_add),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            NewsAndHorVerticalContentButton(
                buttonText = "Share",
                painter = painterResource(id = R.drawable.ic_round_send),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_volume_up),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                NewsAndHorVerticalContentButton(
                    buttonText = "Play",
                    painter = painterResource(id = R.drawable.ic_round_play_arrow),
                    contentDescription = ""
                )
            }

        }
    }
}

@ExperimentalPagerApi
@Composable
@Preview
fun FastLaughPreview() {
    FastLaughScreen()
}