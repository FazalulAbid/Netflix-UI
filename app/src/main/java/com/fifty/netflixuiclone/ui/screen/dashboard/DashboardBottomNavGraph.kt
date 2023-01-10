package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fifty.netflixuiclone.ui.screen.dashboard.downloads.DownloadsScreen
import com.fifty.netflixuiclone.ui.screen.dashboard.fastlaugh.FastLaughScreen
import com.fifty.netflixuiclone.ui.screen.dashboard.search.SearchScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
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