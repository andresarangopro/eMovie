package com.old.domain.databasemanager.localDatabaseEntities

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenresEntity(
    @Embedded
    val movieDetailEntity: MovieDetailEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id_movie"
    )
    val genres: List<GenreEntity>
)