package com.silvozatechnologies.kitsuandroid.data.repository

import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.AnimeApi

class AnimeRepository(private val animeApi: AnimeApi,
                      private val animeDao: AnimeDao) {
    suspend fun getCurrentAnimeList() : Result<List<AnimeEntity>> {
        var result: Result<List<AnimeEntity>> = Result.Error(Exception())

        try {
            val response = animeApi.getCurrentAnimeList(0, 10).await()
            if(response.isSuccessful) {
                response.body()?.let {
                    val animeEntities = mutableListOf<AnimeEntity>()
                    for (media in it.data) {
                        val animeEntity = AnimeEntity(
                                id = media.id,
                                type = media.type,
                                canonicalTitle = media.attributes.canonicalTitle,
                                averageRating = media.attributes.averageRating,
                                popularityRank = media.attributes.popularityRank,
                                status = media.attributes.status,
                                posterImageSmall = media.attributes.posterImageAttributes.small,
                                posterImageOriginal = media.attributes.posterImageAttributes.original
                        )
                        animeEntities.add(animeEntity)
                    }
//                    animeDao.deleteCurrent()
//                    animeDao.insert(animeEntities)
                    result = Result.Success(animeEntities)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = Result.Error(e)
        }

        return result
    }
}