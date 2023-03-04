package com.fifty.netflixuiclone.ui.screen.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.Observer
import com.fifty.netflixuiclone.repository.MovieRepositoryImpl
import com.fifty.netflixuiclone.ui.viewmodel.MoviesViewModel
import com.fifty.netflixuiclone.ui.viewmodel.MoviesViewModelProviderFactory

@ExperimentalMaterial3Api
class DashboardActivity : ComponentActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardMainScreen()
        }
        // Instantiate movies repository.
        val movieRepository = MovieRepositoryImpl()
         val viewModelProviderFactory =
             MoviesViewModelProviderFactory(app = application, moviesRepository = movieRepository)
        viewModel = MoviesViewModel(application, movieRepository)
        viewModel.getTrendingNews()
        viewModel.trendingMovies.observe(this, Observer {
            Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
            Log.e("Hello", it.data.toString())
        })
    }
}