package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterial3Api
@Composable
fun DashboardBottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = DashboardBottomBarScreen.Home.route
    ) {
        composable(route = DashboardBottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = DashboardBottomBarScreen.NewAndHot.route) {
            NewAndHotScreen()
        }
        composable(route = DashboardBottomBarScreen.FastLaugh.route) {
            FastLaughScreen()
        }
        composable(route = DashboardBottomBarScreen.Search.route) {
            SearchScreen()
        }
        composable(route = DashboardBottomBarScreen.Downloads.route) {
            DownloadsScreen()
        }
    }
}