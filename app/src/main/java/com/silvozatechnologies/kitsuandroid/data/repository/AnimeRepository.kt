package com.silvozatechnologies.kitsuandroid.data.repository

import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.AnimeApi
import timber.log.Timber

class AnimeRepository(private val animeApi: AnimeApi,
                      private val animeDao: AnimeDao) {
    suspend fun getCurrentAnime() {
        val response = animeApi.getCurrentAnime(0, 10).await()
        if(response.isSuccessful) {
            response.body()?.let {
                Timber.d(it.toString())
            }
        }
    }
}