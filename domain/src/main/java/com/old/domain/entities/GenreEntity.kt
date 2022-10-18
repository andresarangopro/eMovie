package com.old.domain.entities

import com.old.domain.model.Movie

data class GenreEntity(val id: Int, val name: String) {

    fun toGenre() = Movie(id, name)
}

data class GenresList(val genres: List<GenreEntity>)