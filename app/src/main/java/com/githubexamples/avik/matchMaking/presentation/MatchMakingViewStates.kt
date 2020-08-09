package com.githubexamples.avik.matchMaking.presentation

import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard

sealed class MatchMakingViewStates {
    data class ShowContent(
        val isLoading: Boolean,
        val hasError: Boolean,
        val errorMessage: String,
        val showList: Boolean,
        val matchMakingList: List<EachMatchCard>
    ) : MatchMakingViewStates()

    object ShowLoading : MatchMakingViewStates()
    data class ContentUpdated(
        val isLoading: Boolean,
        val hasError: Boolean,
        val errorMessage: String,
        val updateContentId: String,
        val wasRejected:Boolean,
        val wasAccepted:Boolean
    ) : MatchMakingViewStates()

}
