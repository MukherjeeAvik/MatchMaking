package com.githubexamples.avik.matchMaking.domain.usecase

import com.githubexamples.avik.matchMaking.domain.UseCase
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Observable

class GetMatchMakingCards: UseCase<EachMatchCard>() {
    override fun subscribeForData(vararg params: Any): Observable<EachMatchCard> {
        TODO("Not yet implemented")
    }

}