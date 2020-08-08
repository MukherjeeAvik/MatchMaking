package com.githubexamples.avik.matchMaking.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_main

    override fun getLifeCycleObserver(): LifecycleObserver {
        TODO("Not yet implemented")
    }

    override fun getParentLayForSnackBar(): View? {
        TODO("Not yet implemented")
    }

}