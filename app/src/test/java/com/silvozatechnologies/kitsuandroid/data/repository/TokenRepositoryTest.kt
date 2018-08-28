package com.silvozatechnologies.kitsuandroid.data.repository

import com.google.gson.Gson
import com.silvozatechnologies.kitsuandroid.common.exception.InvalidUsernamePasswordException
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.TokenApi
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetTokensUsingPasswordBody
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.KitsuError
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TokenRepositoryTest {
    @Mock
    private lateinit var tokenApi: TokenApi
    @Mock
    private lateinit var preferences: Preferences

    private lateinit var tokenRepository: TokenRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        tokenRepository = TokenRepository(tokenApi = tokenApi, preferences = preferences)
    }

    @Test
    fun getTokensUsingPassword_validUsernamePassword_saveTokenAndReturnSuccessResult() = runBlocking {
        val username = "username"
        val password = "password"
        val body = GetTokensUsingPasswordBody(username = username, password = password)
        val tokens = Tokens(accessToken = "access_token", refreshToken = "refresh_token", createdAt = System.currentTimeMillis(), expiresIn = 2000000)
        val deferred = async {
            Response.success(tokens)
        }
        Mockito.`when`(tokenApi.getTokensUsingPassword(body)).thenReturn(deferred)

        val result = tokenRepository.getTokensUsingPassword(username = username, password = password)

        verify(preferences, times(1)).putTokens(tokens)
        assert(result is Result.Success && result.data == tokens)
    }

    @Test
    fun getTokensUsingPassword_invalidUsernamePassword_returnInvalidUsernamePasswordErrorResult() = runBlocking {
        val username = "username"
        val password = "password"
        val body = GetTokensUsingPasswordBody(username = username, password = password)
        val kitsuError = KitsuError(error = KitsuError.ERROR_INVALID_GRANT, errorDescription = "")
        val deferred = async {
            Response.error<Tokens>(
                    401,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            Gson().toJson(kitsuError)
                    ))
        }
        Mockito.`when`(tokenApi.getTokensUsingPassword(body)).thenReturn(deferred)

        val result = tokenRepository.getTokensUsingPassword(username = username, password = password)

        assert(result is Result.Error && result.exception is InvalidUsernamePasswordException)
    }

    @Test
    fun getTokensUsingPassword_unexpectedError_returnErrorResult() = runBlocking {
        val username = "username"
        val password = "password"
        val body = GetTokensUsingPasswordBody(username = username, password = password)
        val deferred = async {
            Response.error<Tokens>(
                    500,
                    ResponseBody.create(
                            MediaType.parse("application/json"),
                            Gson().toJson("")
                    ))
        }
        Mockito.`when`(tokenApi.getTokensUsingPassword(body)).thenReturn(deferred)

        val result = tokenRepository.getTokensUsingPassword(username = username, password = password)

        assert(result is Result.Error && result.exception is Exception)
    }
}