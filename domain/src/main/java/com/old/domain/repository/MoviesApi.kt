package com.old.domain.repository

import com.old.domain.entities.MovieDetailsEntity
import com.old.domain.entities.MovieEntity
import com.old.domain.entities.ResultsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val CATEGORY = "category"
        private const val MOVIES = "movie/{$CATEGORY}"
        private const val MOVIE_DETAILS = "movie/{$PARAM_MOVIE_ID}"
    }

    @GET(MOVIES)
    fun movies(@Header("Authorization")  authHeader:String,@Path(CATEGORY) category: String): Call<ResultsEntity>
    @GET(MOVIE_DETAILS)
    fun movieDetails(@Header("Authorization")  authHeader:String,@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>
}
