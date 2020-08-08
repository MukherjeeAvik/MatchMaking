package com.githubexamples.avik.matchMaking.di.modules

import android.app.Application
import android.content.Context
import com.githubexamples.avik.matchMaking.utils.rx.AppSchedulerProvider
import com.githubexamples.avik.matchMaking.utils.rx.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(context: Application): Context = context


    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()


    @Provides
    @Singleton
    fun provideGson() = Gson()


}



