package com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.silvozatechnologies.kitsuandroid.common.coroutine.TestCoroutineContextProvider
import com.silvozatechnologies.kitsuandroid.common.exception.InvalidUsernamePasswordException
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens
import com.silvozatechnologies.kitsuandroid.data.repository.TokenRepository
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SignInViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var tokenRepository: TokenRepository

    @Mock
    private lateinit var intObserver: Observer<Int>

    private lateinit var signInViewModel: SignInViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        signInViewModel = SignInViewModel(tokenRepository = tokenRepository, coroutineContextProvider = TestCoroutineContextProvider())
    }

    @Test
    fun onSignInClicked_validUsernamePassword_flowToHome() = runBlocking {
        val username = "username"
        val password = "password"
        val tokens = Tokens(accessToken = "access_token", refreshToken = "refresh_token", expiresIn = 1800000, createdAt = System.currentTimeMillis())
        val result = Result.Success(tokens)
        Mockito.`when`(tokenRepository.getTokensUsingPassword(username = username, password = password)).thenReturn(result)
        signInViewModel.flow.observeForever(intObserver)

        signInViewModel.onSignInClicked(username = username, password = password)

        verify(intObserver).onChanged(SignInViewModel.FLOW_TO_HOME)
    }

    @Test
    fun onSignInClicked_emptyUsernamePassword_showUsernamePasswordNotEmptySpiel() = runBlocking {
        val username = ""
        val password = ""
        signInViewModel.spiel.observeForever(intObserver)

        signInViewModel.onSignInClicked(username = username, password = password)

        verify(intObserver).onChanged(SignInViewModel.SPIEL_ERROR_USERNAME_PASSWORD_NOT_EMPTY)
    }

    @Test
    fun onSignInClicked_invalidUsernamePassword_showInvalidUsernamePasswordSpiel() = runBlocking {
        val username = "username"
        val password = "password"
        val result = Result.Error(InvalidUsernamePasswordException())
        Mockito.`when`(tokenRepository.getTokensUsingPassword(username = username, password = password)).thenReturn(result)
        signInViewModel.spiel.observeForever(intObserver)

        signInViewModel.onSignInClicked(username = username, password = password)

        verify(intObserver).onChanged(SignInViewModel.SPIEL_ERROR_INVALID_USERNAME_PASSWORD)
    }

    @Test
    fun onSignInClicked_unexpectedError_showDefaultErrorSpiel() = runBlocking {
        val username = "username"
        val password = "password"
        val result = Result.Error(Exception())
        Mockito.`when`(tokenRepository.getTokensUsingPassword(username = username, password = password)).thenReturn(result)
        signInViewModel.spiel.observeForever(intObserver)

        signInViewModel.onSignInClicked(username = username, password = password)

        verify(intObserver).onChanged(SignInViewModel.SPIEL_ERROR_DEFAULT)
    }
}
