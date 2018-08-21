package com.silvozatechnologies.kitsuandroid.ui.home.viewmodel

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.common.coroutine.CoroutineContextProvider
import com.silvozatechnologies.kitsuandroid.data.repository.AnimeRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class HomeAnimeViewModel @Inject constructor(
        private val animeRepository: AnimeRepository,
        private val coroutineContextProvider: CoroutineContextProvider) : ViewModel() {
    private var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.let {
            it.cancel()
        }
    }

    fun getCurrentAnime() {
        job = launch(coroutineContextProvider.uiContext()) {
            animeRepository.getCurrentAnime()
        }
    }
}