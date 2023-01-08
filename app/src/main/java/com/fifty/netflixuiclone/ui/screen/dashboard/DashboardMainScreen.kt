package com.fifty.netflixuiclone.ui.screen.dashboard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.fifty.netflixuiclone.R
import com.fifty.netflixuiclone.util.UiConstants

@ExperimentalMaterial3Api
@Composable
fun DashboardMainScreen() {
    val navController = rememberNavController()
    val lazyListState = rememberLazyListState()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            DashboardBottomNavGraph(navController = navController, lazyListState = lazyListState)
            TopBar(lazyListState)
        }
    }
}

@Composable
fun TopBar(lazyListState: LazyListState) {
    Column {
        // Hiding App bar with netflix logo.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .animateContentSize(animationSpec = tween(durationMillis = 300))
                .height(height = if (lazyListState.isScrolled) 0.dp else PRIMARY_TOP_BAR_HEIGHT)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.netflix_logo),
                        contentDescription = "Netflix Logo"
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        // Screencast button.
                        IconButton(onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_screen_cast),
                                colorFilter = ColorFilter.tint(Color.White),
                                contentDescription = "Screencast"
                            )
                        }
                        // Profile Avatar.
                        IconButton(
                            modifier = Modifier.padding(PaddingValues(10.dp)),
                            onClick = {}) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_img),
                                contentDescription = "Profile"
                            )
                        }
                    }
                }
            }
        }
        // Non hiding app bar with lists.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(SECONDARY_TOP_BAR_HEIGHT)
                .background(
                    color = if (lazyListState.isScrolled)
                        colorResource(id = R.color.transparent_black)
                    else Color.Transparent
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TopAppBarListButton(stringResource(R.string.tv_shows))
                TopAppBarListButton(stringResource(R.string.movies))
                TopAppBarListButton(stringResource(R.string.categories))
            }
        }
    }
}

@Composable
fun RowScope.TopAppBarListButton(buttonText: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, contentColor = Color.White
        )
    ) {
        Text(
            text = buttonText,
            fontSize = 14.sp,
            fontFamily = UiConstants.latoFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
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
            .height(BOTTOM_NAVIGATION_BAR),
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

val PRIMARY_TOP_BAR_HEIGHT = 80.dp
val SECONDARY_TOP_BAR_HEIGHT = 56.dp
val BOTTOM_NAVIGATION_BAR = 65.dp
val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > PRIMARY_TOP_BAR_HEIGHT.value || firstVisibleItemScrollOffset > 0

@ExperimentalMaterial3Api
@Preview()
@Composable
fun DefaultPreview() {
    DashboardMainScreen()
}