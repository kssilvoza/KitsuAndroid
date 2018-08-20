package com.silvozatechnologies.kitsuandroid.di.module

import com.silvozatechnologies.kitsuandroid.common.coroutine.AppCoroutineContextProvider
import com.silvozatechnologies.kitsuandroid.common.coroutine.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoroutineModule {
    @Provides
    @Singleton
    fun provideCoroutineContextProvider() : CoroutineContextProvider {
        return AppCoroutineContextProvider()
    }
}