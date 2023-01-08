package com.fifty.netflixuiclone.ui.screen.dashboard

import com.fifty.netflixuiclone.R

sealed class DashboardBottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : DashboardBottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object NewAndHot : DashboardBottomBarScreen(
        route = "new_and_hot",
        title = "New & Hot",
        icon = R.drawable.ic_fire_solid
    )

    object FastLaugh : DashboardBottomBarScreen(
        route = "fast_laugh",
        title = "Fast Laugh",
        icon = R.drawable.ic_fast_laugh
    )

    object Search : DashboardBottomBarScreen(
        route = "search",
        title = "Search",
        icon = R.drawable.ic_search
    )

    object Downloads : DashboardBottomBarScreen(
        route = "downloads",
        title = "Downloads",
        icon = R.drawable.ic_downloads
    )
}
