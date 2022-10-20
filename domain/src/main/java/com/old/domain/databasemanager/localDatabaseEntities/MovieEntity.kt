package com.old.domain.databasemanager.localDatabaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "movie_id") var id: Int,
    @ColumnInfo(name = "movie_category") var category: String,
    @ColumnInfo(name = "poster_path") var poster_path: String
)