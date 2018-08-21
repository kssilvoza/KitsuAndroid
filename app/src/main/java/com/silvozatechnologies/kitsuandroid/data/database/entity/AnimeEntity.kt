package com.silvozatechnologies.kitsuandroid.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "canonical_title") val canonicalTitle: String,
        @ColumnInfo(name = "average_rating") val averageRating: Float,
        @ColumnInfo(name = "popularity_rank") val popularityRank: Int,
        @ColumnInfo(name = "poster_image_small") val posterImageSmall: String,
        @ColumnInfo(name = "poster_image_original") val posterImageOriginal: String
)