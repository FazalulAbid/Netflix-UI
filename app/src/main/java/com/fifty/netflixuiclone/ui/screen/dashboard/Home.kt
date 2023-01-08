package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(lazyListState: LazyListState) {
    Box(modifier = Modifier.fillMaxSize()) {
        HomeScreenContent(lazyListState)
    }
}

@Composable
fun HomeScreenContent(lazyListState: LazyListState) {
    val number = remember {
        List(size = 25) { it }
    }
    LazyColumn(
        state = lazyListState
    ) {
        items(
            items = number,
            key = { it }
        ) {
            NumberHolder(it)
        }
    }
}

@Composable
fun NumberHolder(number: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = number.toString(),
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }
}


@ExperimentalMaterial3Api
@Composable
@Preview
fun HomeScreenPreview() {
    //HomeScreen(laz)
}


