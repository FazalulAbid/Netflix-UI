package com.fifty.netflixuiclone.repository

import com.fifty.netflixuiclone.api.RetrofitInstance

class MovieRepositoryImpl {

    suspend fun getTrendingMovies() = RetrofitInstance.api.getTrendingMovies()

}