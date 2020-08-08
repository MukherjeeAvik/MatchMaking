package com.githubexamples.avik.matchMaking.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.utils.MM_VERSION

@Database(entities = arrayOf(RandomPeopleItemDbResponse::class), version = MM_VERSION,exportSchema = false)
abstract class MatchMakingDatabase  : RoomDatabase() {
    abstract fun getMoviesDao(): MatchMakingDao
}
