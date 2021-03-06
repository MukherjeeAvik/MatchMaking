package com.githubexamples.avik.matchMaking.domain

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleListResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


interface MatchMakingLocalSource {
    fun getAllSavedPeople(): Observable<List<RandomPeopleItemDbResponse>>
    fun clearAllPeople():Completable
    fun saveListOfPeople(listOfPeople: List<ResultsItem>)
    fun markAsAccepted(guid: String): Completable
    fun markAsRejected(guid: String): Completable
}