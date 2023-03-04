package com.fifty.netflixuiclone.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fifty.netflixuiclone.NetflixApplication
import com.fifty.netflixuiclone.model.MovieResponse
import com.fifty.netflixuiclone.repository.MovieRepositoryImpl
import com.fifty.netflixuiclone.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MoviesViewModel(
    app: Application,
    private val movieRepository: MovieRepositoryImpl
) : AndroidViewModel(app) {

    val trendingMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var trendingMoviesResponse: MovieResponse? = null

    init {
        getTrendingNews()
    }

    fun getTrendingNews() = viewModelScope.launch {
        safeTrendingNewsCall()
    }

    private suspend fun safeTrendingNewsCall() {
        trendingMovies.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = movieRepository.getTrendingMovies()
                trendingMovies.postValue(handleTrendingMoviesResponse(response))
            } else {
                trendingMovies.postValue(Resource.Error("No internet connection."))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> trendingMovies.postValue(Resource.Error("Network Failure."))
                else -> trendingMovies.postValue(Resource.Error("Conversion Error."))
            }
        }
    }

    private fun handleTrendingMoviesResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                trendingMoviesResponse = resultResponse
                return Resource.Success(trendingMoviesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        // Get connectivity manager from application.
        val connectivityManager = getApplication<NetflixApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        // Check for connection.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}