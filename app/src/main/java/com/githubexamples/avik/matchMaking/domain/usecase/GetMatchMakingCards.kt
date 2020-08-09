package com.githubexamples.avik.matchMaking.domain.usecase

import com.githubexamples.avik.matchMaking.domain.MatchMakingRepository
import com.githubexamples.avik.matchMaking.domain.UseCase
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class GetMatchMakingCards @Inject constructor(private val matchMakingRepository: MatchMakingRepository) :
    UseCase<List<EachMatchCard>>() {
    override fun subscribeForData(vararg params: Any): Observable<List<EachMatchCard>> {
        return matchMakingRepository.getListOfPeople()
    }

    fun markAsAccepted(matchCard: EachMatchCard): Completable {
        return matchMakingRepository.markUserAsInterested(matchCard.id)
    }

    fun markAsRejected(matchCard: EachMatchCard): Completable {
        return matchMakingRepository.markUserAsRejected(matchCard.id)
    }


}