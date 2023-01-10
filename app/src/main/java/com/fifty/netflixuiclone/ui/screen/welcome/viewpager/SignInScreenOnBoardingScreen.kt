package com.fifty.netflixuiclone.ui.screen.welcome.viewpager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fifty.netflixuiclone.util.UiConstants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen() {

    val pageState = rememberPagerState()

    Column {
        HorizontalPager(
            count = dataList.size,
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { page ->
            PageUi(pager = dataList[page])
        }
        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = Color.White
        )
        AnimatedVisibility(visible = pageState.currentPage == 3) {

        }
    }
}

@Composable
fun PageUi(pager: SignInScreenViewPager) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = pager.image),
            contentDescription = pager.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to Color.Black,
                        0.25f to Color.Transparent,
                        0.9f to Color.Black
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(300.dp)
                .padding(bottom = 60.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pager.title,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontFamily = UiConstants.latoFontFamily,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = pager.description,
                    fontSize = 16.sp,
                    fontFamily = UiConstants.latoFontFamily,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }
    }
}