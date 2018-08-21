package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class Attributes(
        @SerializedName(value = "canonicalTitle") val canonicalTitle: String,
        @SerializedName(value = "averageRating") val averageRating: Float,
        @SerializedName(value = "popularityRank") val popularityRank: Int,
        @SerializedName(value = "subtype") val type: String,
        @SerializedName(value = "status") val status: String,
        @SerializedName(value = "posterImage") val posterImageAttributes: ImageAttributes
)