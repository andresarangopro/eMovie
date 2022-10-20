package com.old.domain.databasemanager.localDatabaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.old.domain.entities.GenreEntity

@Entity(tableName = "movies_details")
data class MovieDetailEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "movie_id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "poster_path") var poster_path: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "popularity") var popularity: String,
    @ColumnInfo(name = "release_date") var release_date: String,
    @ColumnInfo(name = "vote_average") var vote_average: String
)