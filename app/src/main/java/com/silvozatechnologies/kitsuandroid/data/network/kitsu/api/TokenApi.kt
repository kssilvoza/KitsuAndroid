package com.silvozatechnologies.kitsuandroid.data.network.kitsu.api

import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetTokensUsingFacebookBody
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetTokensUsingPasswordBody
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("/api/oauth/token")
    fun getTokensUsingPassword(@Body body: GetTokensUsingPasswordBody) : Deferred<Response<Tokens>>

    @POST("/api/oauth/token")
    fun getTokensUsingFacebook(@Body body: GetTokensUsingFacebookBody) : Deferred<Response<Tokens>>
}