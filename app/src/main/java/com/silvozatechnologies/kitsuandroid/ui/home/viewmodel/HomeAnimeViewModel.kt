package com.silvozatechnologies.kitsuandroid.ui.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.common.coroutine.CoroutineContextProvider
import com.silvozatechnologies.kitsuandroid.data.database.entity.AnimeEntity
import com.silvozatechnologies.kitsuandroid.data.network.Result
import com.silvozatechnologies.kitsuandroid.data.repository.AnimeRepository
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import timber.log.Timber
import javax.inject.Inject

class HomeAnimeViewModel @Inject constructor(
        private val animeRepository: AnimeRepository,
        private val coroutineContextProvider: CoroutineContextProvider) : ViewModel() {
    val currentAnimeList = MutableLiveData<List<AnimeEntity>>()

    private var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.let {
            it.cancel()
        }
    }

    fun getCurrentAnime() {
        job = launch(coroutineContextProvider.uiContext()) {
            val result = animeRepository.getCurrentAnimeList()
            Timber.d(result.toString())
            when (result) {
                is Result.Success ->
                    currentAnimeList.value = result.data
            }
        }
    }
}