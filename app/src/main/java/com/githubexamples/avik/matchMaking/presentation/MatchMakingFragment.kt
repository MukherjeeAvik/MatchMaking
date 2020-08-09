package com.githubexamples.avik.matchMaking.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import com.githubexamples.avik.matchMaking.R
import com.githubexamples.avik.matchMaking.base.BaseFragment
import com.githubexamples.avik.matchMaking.base.BaseViewHolder
import com.githubexamples.avik.matchMaking.base.ViewModelProviderFactory
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.utils.hide
import com.githubexamples.avik.matchMaking.utils.show
import com.githubexamples.avik.matchMaking.utils.showAsPer
import kotlinx.android.synthetic.main.fragment_match_making.*
import javax.inject.Inject

class MatchMakingFragment : BaseFragment() {

    @Inject
    lateinit var adapter: MatchMakingAdapter

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    companion object {
        const val TAG = "MATCH_MAKING_FRAGMENT"
        fun newInstance(): MatchMakingFragment {

            val args = Bundle()
            val fragment = MatchMakingFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun getLayoutId() = R.layout.fragment_match_making

    override fun getFragmentTag() = TAG

    override fun getLifeCycleObserver(): LifecycleObserver = mainViewModel
    override fun reloadPage() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel =
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeChanges()
        mainViewModel.getRandomListOfPeople()
    }

    private fun observeChanges() {
        mainViewModel.observeViewState().observe(viewLifecycleOwner, Observer { viewStates ->

            when (viewStates) {
                is MatchMakingViewStates.ShowLoading -> {
                    progressBar.show()
                    hideErrors()
                }
                is MatchMakingViewStates.ShowContent -> {
                    progressBar.showAsPer(viewStates.isLoading)
                    if (viewStates.hasError) {
                        showErrors(viewStates.errorMessage)
                    } else {
                        hideErrors()
                    }
                    matchMakingRv.showAsPer(viewStates.showList)
                    if (viewStates.showList) {
                        setUpAdapter(viewStates.matchMakingList)
                    }
                }
                is MatchMakingViewStates.ContentUpdated -> {
                    if (viewStates.hasError) {
                        notifyUserThroughMessage(viewStates.errorMessage)
                    } else {
                        removeErrors()
                        adapter.updatePreference(
                            viewStates.updateContentId,
                            viewStates.wasRejected,
                            viewStates.wasAccepted
                        )
                    }

                }

            }
        })
    }

    private fun setUpAdapter(listOfCountries: List<EachMatchCard>) {
        adapter.registerForCallbacks(object :
            BaseViewHolder.ItemClickedCallback<EachMatchCard> {
            override fun onAccepted(t: EachMatchCard) {
                mainViewModel.markProfileAsAccepted(t)
            }

            override fun onRejected(t: EachMatchCard) {
                mainViewModel.markProfileAsRejected(t)
            }


        })
        adapter.addAll(listOfCountries)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(matchMakingRv)

        matchMakingRv.setHasFixedSize(true)

//        matchMakingRv.layoutManager =  object : LinearLayoutManager(requireContext()) {
//            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
//                lp?.height = height/2
//                lp?.width = width/2
//                return true
//            }
//        }
        matchMakingRv.adapter = adapter

    }

    private fun hideErrors() {
        noContentAvailable.hide()
        retryButton.hide()
    }

    private fun showErrors(errorMessage: String) {
        noContentAvailable.show()
        noContentAvailable.text = errorMessage
        retryButton.show()
    }


}