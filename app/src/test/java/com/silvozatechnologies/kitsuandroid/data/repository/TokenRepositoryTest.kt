package com.silvozatechnologies.kitsuandroid.data.repository

import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.TokenApi
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.GetTokensUsingPasswordBody
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
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
    fun getTokensUsingPassword_validUsernamePassword_successResult() = runBlocking {
        val username = "kimtestemail1@gmail.com"
        val password = "testpassword001"
        val body = GetTokensUsingPasswordBody(username = username, password = password)
        val tokens = Tokens(accessToken = "access_token", refreshToken = "refresh_token", createdAt = System.currentTimeMillis(), expiresIn = 2000000)
        val deferred = async {
            Response.success(tokens)
        }
        Mockito.`when`(tokenApi.getTokensUsingPassword(body)).thenReturn(deferred)

        val result = tokenRepository.getTokensUsingPassword(username = username, password = password)

        assert(result is Result.Success && result.data == tokens)
    }
}