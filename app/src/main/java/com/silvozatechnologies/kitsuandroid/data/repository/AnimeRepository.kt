package com.silvozatechnologies.kitsuandroid.data.repository

import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.AnimeApi
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Attributes
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetMediaResponse
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Media
import retrofit2.Response

class AnimeRepository(private val animeApi: AnimeApi,
                      private val animeDao: AnimeDao) {
    suspend fun getCurrentAnimeList() : Result<List<AnimeEntity>> {
        try {
            val response = animeApi.getCurrentAnimeList(0, 10).await()
            if(response.isSuccessful) {
                response.body()?.let { getMediaResponse ->
                    val animeEntities = mutableListOf<AnimeEntity>()
                    getMediaResponse.data.forEach {
                        animeEntities.add(convertMedia(it))
                    }
//                    animeDao.deleteCurrent()
//                    animeDao.insert(animeEntities)
                    return Result.Success(animeEntities)
                }
            }

            return Result.Error(Exception())
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(e)
        }
    }

    suspend fun getUpcomingAnimeList() : Result<List<AnimeEntity>> {
        try {
            val response = animeApi.getUpcomingAnimeList(0, 10).await()
            if(response.isSuccessful) {
                response.body()?.let { mediaResponse ->
                    val animeEntities = mutableListOf<AnimeEntity>()
                    mediaResponse.data.forEach {
                        animeEntities.add(convertMedia(it))
                    }
//                    animeDao.deleteCurrent()
//                    animeDao.insert(animeEntities)
                    return Result.Success(animeEntities)
                }
            }

            return Result.Error(Exception())
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(e)
        }
    }

    private fun convertMedia(media: Media<Attributes>) : AnimeEntity {
        return AnimeEntity(
                id = media.id,
                type = media.type,
                canonicalTitle = media.attributes.canonicalTitle,
                averageRating = media.attributes.averageRating,
                popularityRank = media.attributes.popularityRank,
                status = media.attributes.status,
                posterImageSmall = media.attributes.posterImageAttributes.small,
                posterImageOriginal = media.attributes.posterImageAttributes.original
        )
    }
}