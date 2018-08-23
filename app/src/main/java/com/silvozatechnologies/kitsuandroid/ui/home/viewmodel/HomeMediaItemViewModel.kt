package com.silvozatechnologies.kitsuandroid.ui.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity

class HomeMediaItemViewModel(animeEntity: AnimeEntity) {
    val posterImage = MutableLiveData<String>()

    init {
        posterImage.value = animeEntity.posterImageSmall
    }
}