package com.silvozatechnologies.kitsuandroid.ui.signin.module

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.kitsuandroid.di.key.ViewModelKey
import com.silvozatechnologies.kitsuandroid.ui.signin.view.activity.SignInActivity
import com.silvozatechnologies.kitsuandroid.ui.signin.viewmodel.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class SignInBuilder {
    @ContributesAndroidInjector()
    internal abstract fun signInActivity() : SignInActivity

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel) : ViewModel
}