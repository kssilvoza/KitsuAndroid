package com.silvozatechnologies.kitsuandroid.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1)
abstract class KitsuAndroidDatabase : RoomDatabase() {
    abstract fun animeDao() : AnimeDao
}