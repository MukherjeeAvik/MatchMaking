package com.githubExamples.mvvm.architecture.data.repos.remote.Utils

import android.app.Application
import com.githubexamples.avik.matchMaking.MyApplication
import com.githubexamples.avik.matchMaking.data.api.utils.NoInternetAvailableException
import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor(private val app: Application) : Interceptor {
    @Throws(NoInternetAvailableException::class, IllegalArgumentException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!(app as MyApplication).isInternetAvailable())
            throw NoInternetAvailableException()

        return chain.proceed(chain.request())
    }


}
