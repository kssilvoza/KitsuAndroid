package com.silvozatechnologies.kitsuandroid.ui.home.view.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity

class MediaDiffCallback(private val oldAnimeEntities: List<AnimeEntity>, private val newAnimeEntities: List<AnimeEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldAnimeEntities.size
    }

    override fun getNewListSize(): Int {
        return newAnimeEntities.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldAnimeEntities[oldItemPosition].id == newAnimeEntities[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldAnimeEntities[oldItemPosition] == newAnimeEntities[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldAnimeEntity = oldAnimeEntities[oldItemPosition]
        val newAnimeEntity = newAnimeEntities[newItemPosition]

        val payload = Bundle()
        if (oldAnimeEntity.canonicalTitle != newAnimeEntity.canonicalTitle) {
            payload.putString(CANONICAL_TITLE_CHANGE, newAnimeEntity.canonicalTitle)
        }
        if (oldAnimeEntity.posterImageSmall != newAnimeEntity.posterImageSmall) {
            payload.putString(POSTER_IMAGE_SMALL_CHANGE, newAnimeEntity.posterImageSmall)
        }

        return if (payload.isEmpty) {
            null
        } else {
            payload
        }
    }

    companion object {
        const val CANONICAL_TITLE_CHANGE = "Canonical Title Change"
        const val POSTER_IMAGE_SMALL_CHANGE = "Poster Image Change"
    }
}