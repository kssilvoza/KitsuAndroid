package com.silvozatechnologies.kitsuandroid.data.network.kitsu.api

import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Attributes
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetMediaResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("/api/edge/anime?fields[anime]=canonicalTitle,averageRating,popularityRank,subtype,status,posterImage&filter[status]=current&sort=popularityRank")
    fun getCurrentAnimeList(@Query("page[offset]") offset: Int, @Query("page[limit]") limit: Int) : Deferred<Response<GetMediaResponse<Attributes>>>

    @GET("/api/edge/anime?fields[anime]=canonicalTitle,averageRating,popularityRank,subtype,status,posterImage&filter[status]=upcoming&sort=popularityRank")
    fun getUpcomingAnimeList(@Query("page[offset]") offset: Int, @Query("page[limit]") limit: Int) : Deferred<Response<GetMediaResponse<Attributes>>>
}