package com.old.domain.databasemanager

import com.old.domain.databasemanager.localDatabaseEntities.MovieDetailEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieWithGenresEntity
import com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity
import com.old.domain.model.MovieDetails

interface ILocalDataSource {
    fun getListMovies(category: String): List<MovieEntity>
    fun createMovies(listMovies: List<MovieEntity>)
    fun getDetailMovie(movieId: Int):MovieDetailEntity
    fun getDetailMovieWithGenres(movieId: Int):MovieWithGenresEntity
    fun createMovieDetail(movieDetails: MovieDetails)
    fun getTrailerByMovieId(movieId: Int):TrailerEntity
    fun createTrailer(trailerEntity: TrailerEntity)
}