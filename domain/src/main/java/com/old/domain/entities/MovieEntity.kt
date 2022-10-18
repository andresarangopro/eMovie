package com.old.domain.entities

import com.old.domain.model.Movie

data class MovieEntity(val id: Int, val poster_path: String) {

    fun toMovie() = Movie(id, poster_path)
}

data class ResultsEntity(val results: List<MovieEntity>)