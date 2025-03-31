package com.example.metereologic_app.data.room

import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun insert(city: String, id: Long?= null)

    suspend fun delete(id: Long)

    suspend fun getAll(): Flow<List<City>>

    suspend fun getByName(city: String): City?

    suspend fun getBy(id: Long): City?
}