package com.old.domain.databasemanager

import androidx.room.*
import com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity


@Dao
interface TrailerDao {

    @Query("SELECT * FROM trailers")
    fun getAllTrailer(): List<TrailerEntity>

    @Query("SELECT * FROM trailers WHERE trailer_id = :id")
    fun getTrailerById(id: Int): TrailerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrailer(trailerEntity: TrailerEntity): Long

    @Delete
    fun deleteCounter(trailerEntity: TrailerEntity): Int

    @Query("DELETE FROM trailers")
    fun nukeTable(): Int
}