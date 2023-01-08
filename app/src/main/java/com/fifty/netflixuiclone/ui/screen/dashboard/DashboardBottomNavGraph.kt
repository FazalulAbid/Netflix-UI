package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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