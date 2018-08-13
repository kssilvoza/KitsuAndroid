package com.silvozatechnologies.kitsuandroid.data.repository

import android.util.Log
import com.google.gson.Gson
import com.silvozatechnologies.kitsuandroid.common.exception.InvalidUsernamePasswordException
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.TokenApi
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetTokensUsingPasswordBody
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.KitsuError
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences

class TokenRepository(private val tokenApi: TokenApi,
                      private val preferences: Preferences) {
    suspend fun getTokensUsingPassword(username: String, password: String) : Result<Tokens> {
        var result: Result<Tokens> = Result.Error(Exception())

        try {
            val body = GetTokensUsingPasswordBody(username = username, password = password)
            val response = tokenApi.getTokensUsingPassword(body).await()
            if (response.isSuccessful) {
                response.body()?.let {
                    preferences.putTokens(it)
                    result = Result.Success(it)
                }
            } else {
                response.errorBody()?.let {
                    val gson = Gson()
                    val kitsuError = gson.fromJson<KitsuError>(it.string(), KitsuError::class.java)
                    result = when(kitsuError.error) {
                        KitsuError.ERROR_INVALID_GRANT ->
                            Result.Error(InvalidUsernamePasswordException())
                        else ->
                            Result.Error(Exception())
                    }
                }
            }
        } catch (e: Exception) {
            result = Result.Error(e)
        }

        return result
    }
}