package com.example.metereologic_app.data.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepositoryImpl(
    private val cityDao: CityDao
) : CityRepository {
    override suspend fun insert(
        city: String,
        country: String,
        id: Long?) {
        val entity = id?.let {
            cityDao.getBy(it)?.copy(
                name = city,
                country = country
            )
        } ?: CityEntity(
                name = city,
                country = country
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
                    name = entity.name,
                    country = entity.country
                )
            }
        }
    }

    override suspend fun getByName(city: String, country: String): City? {
        return cityDao.getByName(city, country)?.let { entity ->
            City(
                id = entity.id,
                name = entity.name,
                country = entity.country
            )
        }
    }

    override suspend fun getBy(id: Long): City? {
        return cityDao.getBy(id)?.let { entity ->
            City(
                id = entity.id,
                name = entity.name,
                country = entity.country
            )
        }
    }
}