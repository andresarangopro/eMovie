package com.old.domain.model

import com.old.domain.empty

data class MovieDetails(
    val id: Int,
    val title: String,
    val poster: String,
    val summary: String,
    val popularity: String,
    val release_date: String,
    val genres: List<Genre>
) {

    companion object {
        val empty = MovieDetails(
            0, String.empty(), String.empty(), String.empty(),
            String.empty(),  String.empty(), emptyList()
        )
    }
}