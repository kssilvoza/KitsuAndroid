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
    val upcomingAnimeList = MutableLiveData<List<AnimeEntity>>()

    private var jobs = mutableListOf<Job>()

    override fun onCleared() {
        super.onCleared()
        jobs.forEach {
            it.cancel()
        }
    }

    fun getCurrentAnime() {
        val job = launch(coroutineContextProvider.uiContext()) {
            val result = animeRepository.getCurrentAnimeList()
            Timber.d(result.toString())
            when (result) {
                is Result.Success ->
                    currentAnimeList.value = result.data
            }
        }
        jobs.add(job)
    }

    fun getUpcomingAnime() {
        val job = launch(coroutineContextProvider.uiContext()) {
            val result = animeRepository.getUpcomingAnimeList()
            Timber.d(result.toString())
            when (result) {
                is Result.Success ->
                    upcomingAnimeList.value = result.data
            }
        }
        jobs.add(job)
    }
}