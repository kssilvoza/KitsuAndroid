package com.silvozatechnologies.kitsuandroid.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.silvozatechnologies.kitsuandroid.BuildConfig
import com.silvozatechnologies.kitsuandroid.data.network.kitsu.api.TokenApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.KITSE_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) : TokenApi {
        return retrofit.create(TokenApi::class.java)
    }
}