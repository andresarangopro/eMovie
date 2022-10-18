package com.old.domain.repository

import com.old.domain.entities.GenreEntity
import com.old.domain.entities.GenresList
import com.old.domain.entities.ResultsEntity
import com.old.domain.model.Genre
import com.old.domain.model.Movie


fun ResultsEntity.toMoviesList():List<Movie> = results.map{
    it.run {
        Movie(
            id,
            poster_path
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