package com.silvozatechnologies.kitsuandroid.ui.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity

class HomeMediaItemViewModel(animeEntity: AnimeEntity) {
    val canonicalTitle = MutableLiveData<String>()
    val posterImage = MutableLiveData<String>()

    init {
        canonicalTitle.value = animeEntity.canonicalTitle
        posterImage.value = animeEntity.posterImageSmall
    }
}