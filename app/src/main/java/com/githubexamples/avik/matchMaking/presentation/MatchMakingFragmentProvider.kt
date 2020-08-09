package com.githubExamples.mvvm.architecture.ui.countryList

import com.githubexamples.avik.matchMaking.presentation.MatchMakingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MatchMakingFragmentProvider {
    @ContributesAndroidInjector(modules = [MatchMakingFragmentModule::class])
    abstract fun provideCountryListFragment(): MatchMakingFragment

}
