package com.old.domain.repository

import com.old.domain.entities.MovieDetailsEntity
import com.old.domain.entities.MovieEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val CATEGORY = "category"
        private const val MOVIES = "movie/{$CATEGORY}"
        private const val MOVIE_DETAILS = "movie/{$PARAM_MOVIE_ID}"
    }

    @GET(MOVIES)
    fun movies(@Path(CATEGORY) category: String): Call<List<MovieEntity>>
    @GET(MOVIE_DETAILS)
    fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailsEntity>
}
