package com.silvozatechnologies.kitsuandroid.di.module

import android.content.Context
import com.silvozatechnologies.kitsuandroid.KitsuAndroidApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: KitsuAndroidApplication) : Context = application.applicationContext
}