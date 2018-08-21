package com.silvozatechnologies.kitsuandroid.data.network.kitsu.model

import com.google.gson.annotations.SerializedName

data class GetMediaResponse<T>(
        @SerializedName("data") val data: List<Media<T>>
)