package com.githubexamples.avik.matchMaking.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseActivity
import com.githubexamples.avik.matchMaking.base.ViewModelProviderFactory
import com.githubexamples.avik.matchMaking.navigation.MainNavigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var mainNavigator: MainNavigator

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        mainNavigator.openMatchMakingPage(R.id.fragmentContainer, false)

    }

    override fun getLayoutId() = R.layout.activity_main

    override fun getLifeCycleObserver() = mainViewModel

    override fun getParentLayForSnackBar() = rootLay

}