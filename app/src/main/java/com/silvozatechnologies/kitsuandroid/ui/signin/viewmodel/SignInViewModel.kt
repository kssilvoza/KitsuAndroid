package com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.common.exception.InvalidUsernamePasswordException
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.repository.TokenRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val tokenRepository: TokenRepository): ViewModel() {
    val flow = MutableLiveData<Int>()
    val spiel = MutableLiveData<Int>()

    fun onSignInClicked(username: String, password: String) {
        if (!username.isEmpty() && !password.isEmpty()) {
            launch(UI) {
                val result = tokenRepository.getTokensUsingPassword(username, password)
                when (result) {
                    is Result.Success ->
                        flow.value = FLOW_TO_HOME
                    is Result.Error -> {
                        when(result.exception) {
                            is InvalidUsernamePasswordException ->
                                spiel.value = SPIEL_ERROR_INVALID_USERNAME_PASSWORD
                            else ->
                                spiel.value = SPIEL_ERROR_DEFAULT
                        }
                    }
                }
            }
        } else {
            spiel.value = SPIEL_ERROR_USERNAME_PASSWORD_NOT_EMPTY
        }
    }

    companion object {
        const val FLOW_TO_HOME = 1

        const val SPIEL_ERROR_USERNAME_PASSWORD_NOT_EMPTY = 1
        const val SPIEL_ERROR_INVALID_USERNAME_PASSWORD = 2
        const val SPIEL_ERROR_DEFAULT = 3
    }
}