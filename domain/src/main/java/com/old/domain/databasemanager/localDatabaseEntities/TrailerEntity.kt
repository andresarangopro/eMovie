package com.old.domain.databasemanager.localDatabaseEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "trailers")
data class TrailerEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "trailer_id") var id: Int,
    @ColumnInfo(name = "trailer_name") var name: String,
    @ColumnInfo(name = "trailer_site") var site: String,
    @ColumnInfo(name = "trailer_key") var key: String,
    @ColumnInfo(name = "trailer_type") var type: String,


)