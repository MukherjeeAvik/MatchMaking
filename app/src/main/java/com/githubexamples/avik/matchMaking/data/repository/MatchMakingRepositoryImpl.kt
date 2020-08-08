package com.githubexamples.avik.matchMaking.data.repository

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.data.mappers.ApiDataToEntityMapper
import com.githubexamples.avik.matchMaking.data.mappers.DbDataToEntityMapper
import com.githubexamples.avik.matchMaking.domain.MatchMakingApiSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingLocalSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingRepository
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MatchMakingRepositoryImpl(
    private val apiSource: MatchMakingApiSource,
    private val localSource: MatchMakingLocalSource,
    private val apiDataToEntityMapper: ApiDataToEntityMapper,
    private val dbDataToEntityMapper: DbDataToEntityMapper
) : MatchMakingRepository {
    override fun getListOfPeople(): Observable<List<EachMatchCard>> {
        return apiSource.getRandomPeopleListFromApi()
            .map {
                syncToLocal(it.results)
                return@map it.results
            }.compose(transformFromApiResponse()).onErrorResumeNext(getDataFromLocal())
    }

    override fun markUserAsInterested(userId: String) {
        localSource.markAsAccepted(userId)
    }

    override fun markUserAsRejected(userId: String) {
        localSource.markAsRejected(userId)
    }


    private fun transformFromApiResponse(): ObservableTransformer<List<ResultsItem?>?, List<EachMatchCard>> {
        return ObservableTransformer { allPeople ->
            allPeople.flatMapIterable { return@flatMapIterable it }
                .map { eachMatch ->
                    return@map apiDataToEntityMapper.mapFrom(eachMatch)
                }.toList().toObservable()

        }

    }

    private fun transformFromDbResponse(): ObservableTransformer<List<RandomPeopleItemDbResponse?>?, List<EachMatchCard>> {
        return ObservableTransformer { allPeople ->
            allPeople.flatMapIterable { return@flatMapIterable it }
                .map { eachMatch ->
                    return@map dbDataToEntityMapper.mapFrom(eachMatch)
                }.toList().toObservable()

        }

    }

    private fun syncToLocal(listOfPeopleFromApi: List<ResultsItem?>?): Completable {
        return Completable.create { emitter ->
            listOfPeopleFromApi?.let {
                localSource.saveListOfPeople(it.filterNotNull())
            }
            emitter.onComplete()

        }
    }

    private fun getDataFromLocal(): Observable<List<EachMatchCard>> {
        return localSource.getAllSavedPeople().compose(transformFromDbResponse())
    }

}