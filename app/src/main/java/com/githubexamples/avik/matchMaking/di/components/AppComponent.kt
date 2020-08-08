package com.githubexamples.avik.matchMaking.di.components

import com.githubexamples.avik.matchMaking.di.modules.RetrofitModule
import android.app.Application
import com.githubexamples.avik.matchMaking.di.ActivityBuilderModule
import com.githubexamples.avik.matchMaking.di.modules.AppModule
import com.githubexamples.avik.matchMaking.MyApplication
import com.githubexamples.avik.matchMaking.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class, AppModule::class,
        RetrofitModule::class,DataModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }


}
