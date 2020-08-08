package com.githubexamples.avik.matchMaking.presentation

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseFragment
import com.githubexamples.avik.matchMaking.base.BaseViewModel

class MatchMakingFragment : BaseFragment() {

    companion object {
        const val TAG = "MATCH_MAKING_FRAGMENT"
        fun newInstance(): MatchMakingFragment {

            val args = Bundle()
            val fragment = MatchMakingFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun getLayoutId()= R.layout.fragment_match_making

    override fun getFragmentTag() = TAG

    override fun getLifeCycleObserver(): LifecycleObserver {
        TODO("Not yet implemented")
    }

    override fun reloadPage() {
        TODO("Not yet implemented")
    }
}