package com.silvozatechnologies.kitsuandroid.data.network.kitsu.api

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("/api/edge/anime?filter[status]=current&sort=popularityRank")
    fun getCurrentAnimeByPopularity(@Query("page[offset]") offset: Int, @Query("page[limit]") limit: Int)
}