package com.fifty.netflixuiclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fifty.netflixuiclone.ui.screen.dashboard.DashboardMainScreen
import com.fifty.netflixuiclone.ui.theme.NetflixUICloneTheme

@ExperimentalMaterial3Api
class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardMainScreen()
        }
    }
}