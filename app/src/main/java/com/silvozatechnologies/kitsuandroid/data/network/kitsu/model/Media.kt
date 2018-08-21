package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class Media<T>(
        @SerializedName(value = "id") val id: String,
        @SerializedName(value = "type") val type: String,
        @SerializedName(value = "attributes") val attributes: T
)
