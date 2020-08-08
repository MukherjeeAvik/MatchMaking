package com.githubexamples.avik.matchMaking.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel:ViewModel() {

   protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
