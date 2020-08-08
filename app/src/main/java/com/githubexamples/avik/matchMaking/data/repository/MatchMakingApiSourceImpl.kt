package com.githubexamples.avik.matchMaking.data.repository

import com.githubexamples.avik.matchMaking.data.api.ApiService
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleListResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.data.mappers.ApiDataToEntityMapper
import com.githubexamples.avik.matchMaking.domain.MatchMakingApiSource
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MatchMakingApiSourceImpl(private val apiService: ApiService) : MatchMakingApiSource {

    override fun getRandomPeopleListFromApi(): Observable<RandomPeopleListResponse> {
        return apiService.getListOfCountriesAsPerZone()
    }


}