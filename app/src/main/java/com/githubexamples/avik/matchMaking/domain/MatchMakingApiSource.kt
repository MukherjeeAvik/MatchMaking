package com.githubexamples.avik.matchMaking.domain

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleListResponse
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Observable


interface MatchMakingApiSource {
      fun getRandomPeopleListFromApi(): Observable<RandomPeopleListResponse>
}