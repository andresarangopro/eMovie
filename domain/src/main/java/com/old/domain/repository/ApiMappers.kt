package com.old.domain.repository

import com.old.domain.databasemanager.localDatabaseEntities.MovieDetailEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieWithGenresEntity
import com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity
import com.old.domain.entities.GenreEntity
import com.old.domain.entities.ResultsEntity
import com.old.domain.model.Genre
import com.old.domain.model.Movie
import com.old.domain.model.MovieDetails
import com.old.domain.model.Trailer


fun ResultsEntity.toMoviesList():List<Movie> = results.map{
    it.run {
        Movie(
            id,
            poster_path
        )
    }
}

fun List<Movie>.toMoviesDatabaseEntity(category: String):List<MovieEntity> = this.map {
    it.run {
        MovieEntity(
            id,
            category,
            poster
        )
    }
}

fun MovieDetails.toMovieDatabaseEntity():MovieDetailEntity= MovieDetailEntity(
    id,
    title,
    poster,
    summary,
    popularity,
    release_date,
    vote_average
)

fun Genre.toGenreDatabaseEntity(movieId: Int): com.old.domain.databasemanager.localDatabaseEntities.GenreEntity = com.old.domain.databasemanager.localDatabaseEntities.GenreEntity(
    id,
    name,
    movieId
)

fun List<MovieEntity>.toMovieList():List<Movie> = this.map {
    it.run {
        Movie(
            id,
            poster_path
        )
    }
}

fun TrailerEntity.toTrailerDomain(): Trailer = Trailer(
    name ,
    site,
    key,
    type
)

fun List<Trailer>.toTrailerDatabaseEntity(movieId: Int): TrailerEntity = TrailerEntity(
    movieId,
    this[0].name,
    this[0].site,
    this[0].key,
    this[0].type
)

fun MovieWithGenresEntity.toMovieDetails(): MovieDetails = MovieDetails(
    movieDetailEntity.id,
    movieDetailEntity.title,
    movieDetailEntity.poster_path,
    movieDetailEntity.overview,
    movieDetailEntity.popularity,
    movieDetailEntity.release_date,
    movieDetailEntity.vote_average,
    genres.toListGenreDomain()
)

fun List<com.old.domain.databasemanager.localDatabaseEntities.GenreEntity>.toListGenreDomain(): List<Genre> = this.map {
    it.run {
        Genre(
            id,
            genre_name
        )
    }
}

fun List<GenreEntity>.toGenreList():List<Genre> = this.map{
    it.run {
        Genre(
            id,
            name
        )
    }
}