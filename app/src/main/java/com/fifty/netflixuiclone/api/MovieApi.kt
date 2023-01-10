package com.fifty.netflixuiclone.api

import com.fifty.netflixuiclone.api.ApiConstants.API_KEY
import com.fifty.netflixuiclone.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/tv")
    suspend fun getTrendingMovies(
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovieResponse>
}