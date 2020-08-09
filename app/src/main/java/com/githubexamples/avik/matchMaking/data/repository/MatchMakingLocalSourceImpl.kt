package com.githubexamples.avik.matchMaking.data.repository

import com.githubexamples.avik.matchMaking.data.db.MatchMakingDatabase
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleListResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.data.mappers.EntityToDbDataMapper
import com.githubexamples.avik.matchMaking.domain.MatchMakingLocalSource
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MatchMakingLocalSourceImpl(
    private val database: MatchMakingDatabase,
    private val entityToDbDataMapper: EntityToDbDataMapper
) :
    MatchMakingLocalSource {
    override fun getAllSavedPeople(): Observable<List<RandomPeopleItemDbResponse>> {
        return database.getMoviesDao().getAllPeople().toObservable()
    }

    override fun clearAllPeople(): Completable {
        return database.getMoviesDao().clearAllPeople()
    }

    override fun saveListOfPeople(listOfPeople: List<ResultsItem>) {
        val list = listOfPeople.map { entityToDbDataMapper.mapFrom(it) }
        database.getMoviesDao().saveAllPeople(list).subscribe()
    }

    override fun markAsAccepted(guid: String): Completable {
        return database.getMoviesDao().markAsAccepted(guid)
    }

    override fun markAsRejected(guid: String): Completable {
        return database.getMoviesDao().markAsRejected(guid)
    }


}