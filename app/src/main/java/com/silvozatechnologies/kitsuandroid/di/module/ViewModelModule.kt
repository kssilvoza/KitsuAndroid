package com.silvozatechnologies.kitsuandroid.di.module

import android.arch.lifecycle.ViewModelProvider
import com.silvozatechnologies.kitsuandroid.common.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}