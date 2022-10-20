package com.old.domain.databasemanager

import androidx.room.*
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE movie_id = :id")
    fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM movie WHERE movie_category = :category")
    fun getMoviesByCategory(category: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movieEntity: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertMovieList(movieEntityList: List<MovieEntity>)

    @Delete
    fun deleteCounter(movieEntity: MovieEntity): Int

    @Query("DELETE FROM movie")
    fun nukeTable(): Int
}