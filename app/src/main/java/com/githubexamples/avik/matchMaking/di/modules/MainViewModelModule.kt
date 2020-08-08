package com.githubexamples.avik.matchMaking.di.modules

import androidx.lifecycle.ViewModel
import com.githubexamples.avik.matchMaking.di.scopes.ViewModelKey
import com.githubexamples.avik.matchMaking.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
