package com.githubexamples.avik.matchMaking.data.api

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=10")
    fun getListOfCountriesAsPerZone(): Observable<RandomPeopleListResponse>
}