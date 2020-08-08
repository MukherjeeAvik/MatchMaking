package com.githubexamples.avik.matchMaking.navigation

import androidx.fragment.app.FragmentManager
import com.githubexamples.avik.matchMaking.base.BaseNavigator
import com.githubexamples.avik.matchMaking.presentation.MatchMakingFragment
import javax.inject.Inject

class MainNavigator @Inject constructor(fragmentManager: FragmentManager) :
    BaseNavigator(fragmentManager) {

    fun openMatchMakingPage(container: Int, addToBackStack: Boolean) {

        var fragment = findFragment(MatchMakingFragment.TAG)
        if (fragment == null) {
            fragment = MatchMakingFragment.newInstance()
        }

        showFragmentWithAnimation(
            fragment,
            MatchMakingFragment.TAG,
            addToBackStack,
            container
        )

    }
}