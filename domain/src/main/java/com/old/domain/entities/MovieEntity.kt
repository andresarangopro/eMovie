package com.old.domain.entities

import com.old.domain.model.Movie

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}
