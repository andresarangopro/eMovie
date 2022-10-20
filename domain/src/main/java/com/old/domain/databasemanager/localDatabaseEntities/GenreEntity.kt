package com.old.domain.databasemanager.localDatabaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "genres",
    foreignKeys = [ForeignKey(
        entity = MovieDetailEntity::class,
        parentColumns = arrayOf("movie_id"),
        childColumns = arrayOf("id_movie"),
        onDelete = ForeignKey.CASCADE
    )]
    )
data class GenreEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "genre_id") var id: Int,
    @ColumnInfo(name = "genre_name") var genre_name: String,
    @ColumnInfo(name = "id_movie",index = true)
    val id_movie: Int
)