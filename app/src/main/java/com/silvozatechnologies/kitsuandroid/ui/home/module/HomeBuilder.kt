package com.silvozatechnologies.kitsuandroid.ui.home.module

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.di.key.ViewModelKey
import com.silvozatechnologies.kitsuandroid.ui.home.view.activity.HomeActivity
import com.silvozatechnologies.kitsuandroid.ui.home.view.fragment.HomeAnimeFragment
import com.silvozatechnologies.kitsuandroid.ui.home.viewmodel.HomeAnimeViewModel
import com.silvozatechnologies.kitsuandroid.ui.signin.view.activity.SignInActivity
import com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HomeBuilder {
    @ContributesAndroidInjector()
    internal abstract fun homeActivity() : HomeActivity

    @ContributesAndroidInjector()
    internal abstract fun homeAnimeFragment() : HomeAnimeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeAnimeViewModel::class)
    abstract fun bindHomeAnimeViewModel(viewModel: HomeAnimeViewModel) : ViewModel
}