package com.silvozatechnologies.kitsuandroid.di.component

import com.silvozatechnologies.kitsuandroid.KitsuAndroidApplication
import com.silvozatechnologies.kitsuandroid.di.module.*
import com.silvozatechnologies.kitsuandroid.ui.signin.module.SignInBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,

    ApiModule::class,
    AppModule::class,
    DataModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,

    SignInBuilder::class
])
interface ApplicationComponent : AndroidInjector<KitsuAndroidApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<KitsuAndroidApplication>()
}