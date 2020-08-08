package com.githubexamples.avik.matchMaking.di.modules

import android.app.Application
import com.githubExamples.mvvm.architecture.data.repos.remote.Utils.RequestInterceptor
import com.githubexamples.avik.matchMaking.di.qualifiers.BaseUrl
import com.githubexamples.avik.matchMaking.data.api.ApiService

import dagger.Module
import dagger.Provides
import com.githubexamples.avik.matchMaking.utils.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val READ_TIMEOUT_SECONDS = 60
    private val WRITE_TIMEOUT_SECONDS = 60
    private val CONNECT_TIMEOUT_SECONDS = 10
    private val CACHE_50MB = 50 * 1024 * 1024L

    @Provides
    @Singleton
    fun provideOkHttp(
        dataCache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .cache(dataCache)
            .connectTimeout(CONNECT_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(requestInterceptor)

    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient.Builder, @BaseUrl baseUrl: String): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build()).build()
    }

    @Provides
    @Singleton
    fun provideDataCache(context: Application): Cache = Cache(context.cacheDir, CACHE_50MB)


    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
    }


    @Provides
    @Singleton
    fun provideCustomInterceptor(app: Application) = RequestInterceptor(app)


    @Provides
    @Singleton
    fun provideItemService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @BaseUrl
    fun getBaseUrlForRetrofit() = BASE_URL


}
