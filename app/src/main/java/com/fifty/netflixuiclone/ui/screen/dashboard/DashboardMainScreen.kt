package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardMainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navController) }) {
        DashboardBottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        DashboardBottomBarScreen.Home,
        DashboardBottomBarScreen.NewAndHot,
        DashboardBottomBarScreen.FastLaugh,
        DashboardBottomBarScreen.Search,
        DashboardBottomBarScreen.Downloads
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(60.dp),
        backgroundColor = Color.Black
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: DashboardBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (selected) Color.White else Color.Gray
            )
        },
        icon = {
            Image(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier
                    .width(20.dp)
                    .padding(bottom = 4.dp),
                colorFilter = if (selected) ColorFilter.tint(Color.White) else ColorFilter.tint(
                    Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
        },
        selected = selected,
        selectedContentColor = Color.Red,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        alwaysShowLabel = false,
        onClick = {
            navController.navigate(screen.route) {
                // popUpTo(navController.graph.findStartDestination().id) --Go Directly to Start destination.
                launchSingleTop = true
            }
        },
    )
}

@Preview()
@Composable
fun DefaultPreview() {
    DashboardMainScreen()
}