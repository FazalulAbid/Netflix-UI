package com.fifty.netflixuiclone.viewpager

import androidx.annotation.DrawableRes
import com.fifty.netflixuiclone.R

data class SignInScreenViewPager(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

val dataList = listOf(
    SignInScreenViewPager(
        R.drawable.net_home1,
        "Unlimited entertainment, one low price.",
        "Everything on Netflix, starting at just Rs.149."
    ),
    SignInScreenViewPager(
        R.drawable.net_home2,
        "Download and watch offline",
        "Always have something to watch offline."
    ),
    SignInScreenViewPager(
        R.drawable.net_home3,
        "No annoying contracts",
        "Join today, cancel at any time."
    ),
    SignInScreenViewPager(
        R.drawable.net_home1,
        "Watch everywhere",
        "Stream on your phone, tablet, laptop, TV and more."
    )
)
