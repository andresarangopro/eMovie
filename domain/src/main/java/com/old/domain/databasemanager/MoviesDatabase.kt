package com.old.domain.databasemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.old.domain.databasemanager.localDatabaseEntities.GenreEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieDetailEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity
import com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity


@Database(entities = [MovieEntity::class,
    MovieDetailEntity::class,
    GenreEntity::class,
    TrailerEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun trailerDao(): TrailerDao
}