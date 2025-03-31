package com.example.metereologic_app.data.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepositoryImpl(
    private val cityDao: CityDao
) : CityRepository {
    override suspend fun insert(
        city: String,
        id: Long?) {
        val entity = id?.let {
            cityDao.getBy(it)?.copy(
                name = city,
            )
        } ?: CityEntity(
                name = city,
        )

        cityDao.insert(entity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = cityDao.getBy(id) ?: return
        cityDao.delete(existingEntity)
    }

    override suspend fun getAll(): Flow<List<City>> {
        return cityDao.getAll().map { entities ->
            entities.map { entity ->
                City(
                    id = entity.id,
                    name = entity.name
                )
            }
        }
    }

    override suspend fun getByName(city: String): City? {
        return cityDao.getByName(city)?.let { entity ->
            City(
                id = entity.id,
                name = entity.name
            )
        }
    }

    override suspend fun getBy(id: Long): City? {
        return cityDao.getBy(id)?.let { entity ->
            City(
                id = entity.id,
                name = entity.name
            )
        }
    }
}