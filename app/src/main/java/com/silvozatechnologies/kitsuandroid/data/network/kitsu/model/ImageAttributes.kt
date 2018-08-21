package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class ImageAttributes(
        @SerializedName(value = "small") val small: String,
        @SerializedName(value = "original") val original: String
)