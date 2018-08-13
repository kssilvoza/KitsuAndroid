package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class GetTokensUsingFacebookBody(@SerializedName("assertion") val accessToken: String) {
    @SerializedName("grant_type") val grantType = "assertion"
    @SerializedName("provider") val provider = "facebook"
}