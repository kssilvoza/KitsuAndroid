package com.silvozatechnologies.kitsuandroid.di.module

import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.AnimeApi
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.TokenApi
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import com.silvozatechnologies.kitsuandroid.data.repository.AnimeRepository
import com.silvozatechnologies.kitsuandroid.data.repository.TokenRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideTokenRepository(tokenApi: TokenApi, preferences: Preferences) : TokenRepository {
        return TokenRepository(tokenApi, preferences)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(animeApi: AnimeApi, animeDao: AnimeDao) : AnimeRepository {
        return AnimeRepository(animeApi, animeDao)
    }
}