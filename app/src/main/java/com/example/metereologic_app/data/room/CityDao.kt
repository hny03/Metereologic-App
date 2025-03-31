package com.example.metereologic_app.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityEntity: CityEntity)

    @Delete
    suspend fun delete(cityEntity: CityEntity)

    @Query("SELECT * FROM cities WHERE name LIKE :city LIMIT 1")
    suspend fun getByName(city: String): CityEntity?

    @Query("SELECT * FROM cities")
    fun getAll(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getBy(id: Long): CityEntity?
}