package com.githubexamples.avik.matchMaking.presentation

import androidx.lifecycle.LifecycleObserver
import com.githubexamples.avik.matchMaking.base.BaseViewModel
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.domain.usecase.GetMatchMakingCards
import com.githubexamples.avik.matchMaking.utils.BLANK
import com.githubexamples.avik.matchMaking.utils.CANT_UPDATE
import com.githubexamples.avik.matchMaking.utils.SOMETHING_WENT_WRONG
import com.githubexamples.avik.matchMaking.utils.SingleLiveEvent
import com.githubexamples.avik.matchMaking.utils.rx.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val matchMakingUseCase: GetMatchMakingCards,
    private val schedulers: SchedulerProvider
) :
    BaseViewModel(), LifecycleObserver {

    private val listOfMatchMakingCards: SingleLiveEvent<MatchMakingViewStates> by lazy { SingleLiveEvent<MatchMakingViewStates>() }

    fun observeViewState() = listOfMatchMakingCards

    fun getRandomListOfPeople() {
        compositeDisposable.add(
            matchMakingUseCase.subscribeForData()
                .doOnSubscribe { listOfMatchMakingCards.postValue(MatchMakingViewStates.ShowLoading) }
                .subscribeOn(schedulers.computation())
                .subscribe({
                    val viewState = MatchMakingViewStates.ShowContent(
                        showList = it.isNotEmpty(),
                        isLoading = false,
                        hasError = it.isEmpty(),
                        errorMessage = if (it.isEmpty()) SOMETHING_WENT_WRONG else "",
                        matchMakingList = ArrayList(it)
                    )
                    listOfMatchMakingCards.postValue(viewState)
                }, {
                    it.printStackTrace()
                    val viewState = MatchMakingViewStates.ShowContent(
                        showList = false,
                        isLoading = false,
                        hasError = true,
                        errorMessage = SOMETHING_WENT_WRONG,
                        matchMakingList = ArrayList()
                    )
                    listOfMatchMakingCards.postValue(viewState)

                })
        )
    }

    fun markProfileAsAccepted(matchCard: EachMatchCard) {
        compositeDisposable.add(matchMakingUseCase.markAsAccepted(matchCard)
            .doOnSubscribe { listOfMatchMakingCards.postValue(MatchMakingViewStates.ShowLoading) }
            .subscribeOn(schedulers.io())
            .subscribe({

                val viewState = MatchMakingViewStates.ContentUpdated(

                    isLoading = false,
                    hasError = false,
                    errorMessage = BLANK,
                    updateContentId = matchCard.id,
                    wasAccepted = true,
                    wasRejected = false


                )
                listOfMatchMakingCards.postValue(viewState)
            }, {

                val viewState = MatchMakingViewStates.ContentUpdated(

                    isLoading = false,
                    hasError = true,
                    errorMessage = CANT_UPDATE,
                    updateContentId = matchCard.id,
                    wasAccepted = false,
                    wasRejected = false

                )
                listOfMatchMakingCards.postValue(viewState)
            })
        )
    }

    fun markProfileAsRejected(matchCard: EachMatchCard) {
        compositeDisposable.add(matchMakingUseCase.markAsRejected(matchCard)
            .doOnSubscribe { listOfMatchMakingCards.postValue(MatchMakingViewStates.ShowLoading) }
            .subscribeOn(schedulers.io())
            .subscribe({

                val viewState = MatchMakingViewStates.ContentUpdated(

                    isLoading = false,
                    hasError = false,
                    errorMessage = BLANK,
                    updateContentId = matchCard.id,
                    wasAccepted = false,
                    wasRejected = true

                )
                listOfMatchMakingCards.postValue(viewState)
            }, {

                val viewState = MatchMakingViewStates.ContentUpdated(

                    isLoading = false,
                    hasError = true,
                    errorMessage = CANT_UPDATE,
                    updateContentId = matchCard.id,
                    wasAccepted = false,
                    wasRejected = false

                )
                listOfMatchMakingCards.postValue(viewState)
            })
        )

    }

}