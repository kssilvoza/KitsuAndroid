package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class GetTokensUsingPasswordBody(
        @SerializedName("username") val username: String,
        @SerializedName("password") val password: String) {
    @SerializedName("grant_type") val grantType = "password"
}