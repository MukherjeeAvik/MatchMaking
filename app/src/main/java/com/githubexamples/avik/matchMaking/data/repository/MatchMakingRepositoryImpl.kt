package com.githubexamples.avik.matchMaking.data.repository

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.data.mappers.ApiDataToEntityMapper
import com.githubexamples.avik.matchMaking.data.mappers.DbDataToEntityMapper
import com.githubexamples.avik.matchMaking.domain.MatchMakingApiSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingLocalSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingRepository
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.utils.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class MatchMakingRepositoryImpl(
    private val apiSource: MatchMakingApiSource,
    private val localSource: MatchMakingLocalSource,
    private val apiDataToEntityMapper: ApiDataToEntityMapper,
    private val dbDataToEntityMapper: DbDataToEntityMapper,
    private val schedulers: SchedulerProvider
) : MatchMakingRepository {
    override fun getListOfPeople(): Observable<List<EachMatchCard>> {

//        return Observable.create { emitter ->
//
//            apiSource.getRandomPeopleListFromApi()
//                .map {
//                    syncToLocal(it.results).subscribe()
//                    return@map it.results
//                }
//                .compose(transformFromApiResponse())
//                .subscribe({
//                    emitter.onNext(it)
//
//                }, {
//                    val a = 1
//                    it.printStackTrace()
//                    getDataFromLocal().subscribe({
//                        emitter.onNext(it)
//                    },{
//                        val a = 1
//                        it.printStackTrace()
//
//                    })
//                })
//
//        }

        return apiSource.getRandomPeopleListFromApi()
            .map {
                syncToLocal(it.results).subscribeOn(schedulers.io()).subscribe()
                return@map it.results
            }
            .compose(transformFromApiResponse())
            .onErrorResumeNext(
                getDataFromLocal()
            )
    }

    override fun markUserAsInterested(userId: String): Completable {
        return localSource.markAsAccepted(userId)
    }

    override fun markUserAsRejected(userId: String): Completable {
        return localSource.markAsRejected(userId)
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
            localSource.clearAllPeople().subscribe({
                listOfPeopleFromApi?.let {
                    localSource.saveListOfPeople(it.filterNotNull())
                }
                emitter.onComplete()
            }, {
                emitter.onComplete()
            })


        }
    }

    private fun getDataFromLocal(): Observable<List<EachMatchCard>> {
        return localSource.getAllSavedPeople().compose(transformFromDbResponse())
    }

}