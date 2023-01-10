package com.fifty.netflixuiclone.ui.screen.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.fifty.netflixuiclone.ui.viewmodel.MoviesViewModel

@ExperimentalMaterial3Api
class DashboardActivity : ComponentActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardMainScreen()
        }
        // initComponents()
    }

    private fun initComponents() {
        // Instantiate movies repository.
        // val movieRepository = MovieRepositoryImpl()
        // val viewModelProviderFactory =
        //     MoviesViewModelProviderFactory(app = application, movieRepository = movieRepository)
        // viewModel = ViewModelProvider(this, viewModelProviderFactory)[MoviesViewModel::class.java]
    }

}