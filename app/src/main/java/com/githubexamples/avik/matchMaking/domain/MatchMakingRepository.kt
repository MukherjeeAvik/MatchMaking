package com.githubexamples.avik.matchMaking.domain

import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Completable
import io.reactivex.Observable


interface MatchMakingRepository {
    fun getListOfPeople(): Observable<List<EachMatchCard>>
    fun markUserAsInterested(userId: String): Completable
    fun markUserAsRejected(userId: String): Completable

}