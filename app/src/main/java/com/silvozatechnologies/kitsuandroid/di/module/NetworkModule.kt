package com.silvozatechnologies.kitsuandroid.di.module

import com.silvozatechnologies.kitsuandroid.BuildConfig
import com.silvozatechnologies.kitsuandroid.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                )

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
    }
}