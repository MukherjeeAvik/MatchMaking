package com.githubexamples.avik.matchMaking.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface MatchMakingDao{

    @Query("SELECT * FROM MATCH_MAKING")
    fun getAllPeople(): Observable<List<RandomPeopleItemDbResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPeople(movies: List<RandomPeopleItemDbResponse>)

    @Query("DELETE FROM MATCH_MAKING")
    fun clearAllPeople()

    @Query("UPDATE MATCH_MAKING SET isInterested = 1 , hasDeclined = 0 WHERE userId = :userId")
    fun markAsAccepted(userId:String)

    @Query("UPDATE MATCH_MAKING SET isInterested = 0 , hasDeclined = 1 WHERE userId = :userId")
    fun markAsRejected(userId:String)


}