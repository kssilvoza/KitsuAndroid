package com.silvozatechnologies.kitsuandroid.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "type") var type: String,
        @ColumnInfo(name = "canonical_title") var canonicalTitle: String,
        @ColumnInfo(name = "average_rating") var averageRating: Float,
        @ColumnInfo(name = "popularity_rank") var popularityRank: Int,
        @ColumnInfo(name = "status") var status: String,
        @ColumnInfo(name = "poster_image_small") var posterImageSmall: String,
        @ColumnInfo(name = "poster_image_original") var posterImageOriginal: String
)