package com.example.metereologic_app.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CityEntity::class],
    version = 1
)
abstract class CityDataBase : RoomDatabase(){

    abstract val cityDao: CityDao
}

object CityDatabaseProvider {

    @Volatile
    private var INSTANCE: CityDataBase? = null

    fun provide(context: Context): CityDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CityDataBase::class.java,
                "metereologic-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}