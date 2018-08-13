package com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.repository.TokenRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val tokenRepository: TokenRepository): ViewModel() {
    val flow = MutableLiveData<Int>()
    val spiel = MutableLiveData<String>()

    fun onSignInClicked(username: String, password: String) {
        if (!username.isEmpty() && !password.isEmpty()) {
            launch(UI) {
                val result = tokenRepository.getTokensUsingPassword(username, password)
                when (result) {
                    is Result.Success -> flow.value = FLOW_TO_HOME
                    is Result.Error -> {
                        spiel.value = "Something went wrong. Please try again later"
                    }
                }
            }
        } else {
            spiel.value = "Username or password should not be empty"
        }
    }

    companion object {
        const val FLOW_TO_HOME = 1
    }
}