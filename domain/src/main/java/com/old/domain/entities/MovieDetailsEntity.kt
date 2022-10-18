package com.old.domain.entities

import com.old.domain.empty
import com.old.domain.model.MovieDetails
import com.old.domain.repository.toGenreList

data class MovieDetailsEntity(
    private val id: Int,
    private val title: String,
    private val poster_path: String,
    private val overview: String,
    private val popularity: String,
    private val release_date: String,
    private val genres: List<GenreEntity>
) {

    companion object {
        val empty = MovieDetailsEntity(
            0, String.empty(), String.empty(), String.empty(),
            String.empty(), String.empty(), emptyList()
        )
    }

    fun toMovieDetails():MovieDetails =
        MovieDetails(
            id,
            title,
            poster_path,
            overview,
            popularity,
            release_date,
            genres.toGenreList())

}
