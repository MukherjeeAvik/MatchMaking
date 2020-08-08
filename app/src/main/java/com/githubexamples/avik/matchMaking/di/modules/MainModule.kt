package com.githubexamples.avik.matchMaking.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.githubexamples.avik.matchMaking.presentation.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideFragmentManager(activity: MainActivity): FragmentManager =
        (activity as AppCompatActivity).supportFragmentManager





}


