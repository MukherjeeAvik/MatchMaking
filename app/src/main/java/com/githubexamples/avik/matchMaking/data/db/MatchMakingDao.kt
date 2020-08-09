package com.githubexamples.avik.matchMaking.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface MatchMakingDao{

    @Query("SELECT * FROM MATCH_MAKING")
    fun getAllPeople(): Single<List<RandomPeopleItemDbResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPeople(movies: List<RandomPeopleItemDbResponse>):Completable

    @Query("DELETE FROM MATCH_MAKING")
    fun clearAllPeople():Completable

    @Query("UPDATE MATCH_MAKING SET isInterested = 1 , hasDeclined = 0 WHERE userId = :userId")
    fun markAsAccepted(userId:String):Completable

    @Query("UPDATE MATCH_MAKING SET isInterested = 0 , hasDeclined = 1 WHERE userId = :userId")
    fun markAsRejected(userId:String) :Completable


}