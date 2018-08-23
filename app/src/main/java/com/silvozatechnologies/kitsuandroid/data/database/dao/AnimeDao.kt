package com.silvozatechnologies.kitsuandroid.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity

@Dao
interface AnimeDao {
    @Insert
    fun insert(animeEntities: List<AnimeEntity>)

    @Query(value = "DELETE FROM anime WHERE status = 'current'")
    fun deleteCurrent()
}