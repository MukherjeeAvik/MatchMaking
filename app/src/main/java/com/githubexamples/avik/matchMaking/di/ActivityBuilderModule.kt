package com.githubexamples.avik.matchMaking.di

import com.githubExamples.mvvm.architecture.ui.countryList.MatchMakingFragmentModule
import com.githubExamples.mvvm.architecture.ui.countryList.MatchMakingFragmentProvider
import com.githubexamples.avik.matchMaking.di.modules.MainModule
import com.githubexamples.avik.matchMaking.di.modules.MainViewModelModule
import com.githubexamples.avik.matchMaking.di.scopes.MainScope
import com.githubexamples.avik.matchMaking.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class,
            MainViewModelModule::class,
            MatchMakingFragmentModule::class,
            MatchMakingFragmentProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

}
