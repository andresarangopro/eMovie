package com.old.domain.databasemanager

import androidx.room.*
import com.old.domain.databasemanager.localDatabaseEntities.GenreEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieDetailEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieWithGenresEntity


@Dao
interface MovieDetailDao {

    @Query("SELECT * FROM movies_details")
    fun getAllMovies(): List<MovieDetailEntity>

    @Query("SELECT * FROM movies_details WHERE movie_id = :id")
    fun getMovieById(id: Int): MovieDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieDetailEntity: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenre(vararg genreEntity: GenreEntity)

    @Transaction
    @Query("SELECT * FROM movies_details WHERE movie_id = :id")
    fun getByMovieId(id: Int): MovieWithGenresEntity

    @Delete
    fun deleteCounter(movieEntity: MovieEntity)

    @Query("DELETE FROM movies_details")
    fun nukeTable(): Int
}