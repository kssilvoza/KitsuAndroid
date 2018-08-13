package com.silvozatechnologies.kitsuandroid.di.module

import android.content.Context
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun providePreferences(context: Context) : Preferences {
        return Preferences(context)
    }
}