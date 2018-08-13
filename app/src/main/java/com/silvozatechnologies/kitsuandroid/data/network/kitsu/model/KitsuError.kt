package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class KitsuError(
        @SerializedName("error") val error: String,
        @SerializedName("error_description") val errorDescription: String) {
    companion object {
        const val ERROR_INVALID_GRANT = "invalid_grant"
    }
}