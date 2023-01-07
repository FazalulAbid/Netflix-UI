package com.fifty.netflixuiclone.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.fifty.netflixuiclone.R

class UiConstants {
    companion object {
        val latoFontFamily = FontFamily(
            Font(R.font.lato_thin, FontWeight.Thin),
            Font(R.font.lato_light, FontWeight.Light),
            Font(R.font.lato_regular, FontWeight.Normal),
            Font(R.font.lato_bold, FontWeight.Bold),
            Font(R.font.lato_black, FontWeight.Black)
        )
    }
}