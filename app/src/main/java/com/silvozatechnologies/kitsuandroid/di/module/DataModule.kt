package com.silvozatechnologies.kitsuandroid.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.silvozatechnologies.kitsuandroid.data.database.KitsuAndroidDatabase
import com.silvozatechnologies.kitsuandroid.data.database.dao.AnimeDao
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context) : KitsuAndroidDatabase {
        return Room.databaseBuilder(context, KitsuAndroidDatabase::class.java, "kitsu_android").build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(kitsuAndroidDatabase: KitsuAndroidDatabase) : AnimeDao {
        return kitsuAndroidDatabase.animeDao()
    }

    @Provides
    @Singleton
    fun providePreferences(context: Context) : Preferences {
        return Preferences(context)
    }
}