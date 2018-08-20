package com.silvozatechnologies.kitsuandroid.data.preferences

import android.content.Context
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.model.Tokens

class Preferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, 0)

    fun putTokens(tokens: Tokens) {
        val sharedPreferenceEditor = sharedPreferences.edit()
        sharedPreferenceEditor.putString(KEY_TOKENS_ACCESS_TOKEN, tokens.accessToken)
        sharedPreferenceEditor.putString(KEY_TOKENS_REFRESH_TOKEN, tokens.refreshToken)
        sharedPreferenceEditor.putLong(KEY_TOKENS_EXPIRES_IN, tokens.expiresIn)
        sharedPreferenceEditor.putLong(KEY_TOKENS_CREATED_AT, tokens.createdAt)
        sharedPreferenceEditor.apply()
    }

    fun getAccessToken() : String {
        return sharedPreferences.getString(KEY_TOKENS_ACCESS_TOKEN, "")!!
    }

    companion object {
        private const val SHARED_PREFERENCE_NAME = "kitsuandroid.pref"

        private const val KEY_TOKENS_ACCESS_TOKEN = "KEY_TOKENS_ACCESS_TOKEN"
        private const val KEY_TOKENS_REFRESH_TOKEN = "KEY_TOKENS_REFRESH_TOKEN"
        private const val KEY_TOKENS_EXPIRES_IN = "KEY_TOKENS_EXPIRES_IN"
        private const val KEY_TOKENS_CREATED_AT = "KEY_TOKENS_CREATED_AT"
    }
}