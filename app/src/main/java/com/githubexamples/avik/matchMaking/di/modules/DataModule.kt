package com.githubexamples.avik.matchMaking.di.modules

import android.content.Context
import androidx.room.Room
import com.githubexamples.avik.matchMaking.data.api.ApiService
import com.githubexamples.avik.matchMaking.data.db.MatchMakingDatabase
import com.githubexamples.avik.matchMaking.data.mappers.ApiDataToEntityMapper
import com.githubexamples.avik.matchMaking.data.mappers.DbDataToEntityMapper
import com.githubexamples.avik.matchMaking.data.mappers.EntityToDbDataMapper
import com.githubexamples.avik.matchMaking.data.repository.MatchMakingApiSourceImpl
import com.githubexamples.avik.matchMaking.data.repository.MatchMakingLocalSourceImpl
import com.githubexamples.avik.matchMaking.data.repository.MatchMakingRepositoryImpl
import com.githubexamples.avik.matchMaking.domain.MatchMakingApiSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingLocalSource
import com.githubexamples.avik.matchMaking.domain.MatchMakingRepository
import com.githubexamples.avik.matchMaking.utils.MATCH_MAKING_DB
import com.githubexamples.avik.matchMaking.utils.rx.AppSchedulerProvider
import com.githubexamples.avik.matchMaking.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MatchMakingDatabase {
        return Room.databaseBuilder(
            context,
            MatchMakingDatabase::class.java,
            MATCH_MAKING_DB
        ).build()
    }

    @Singleton
    @Provides
    fun provideMatchMakingRepository(
        apiSource: MatchMakingApiSource,
        localSource: MatchMakingLocalSource,
        apiDataToEntityMapper: ApiDataToEntityMapper,
        dbDataToEntityMapper: DbDataToEntityMapper,
        schedulers: SchedulerProvider
    ): MatchMakingRepository {
        return MatchMakingRepositoryImpl(
            apiSource,
            localSource,
            apiDataToEntityMapper,
            dbDataToEntityMapper,
            schedulers

        )
    }

    @Provides
    fun provideMatchMakingLocalSource(
        database: MatchMakingDatabase,
        entityToDbDataMapper: EntityToDbDataMapper
    ): MatchMakingLocalSource {
        return MatchMakingLocalSourceImpl(database, entityToDbDataMapper)
    }

    @Provides
    fun provideMatchMakingRemoteSource(api: ApiService): MatchMakingApiSource {
        return MatchMakingApiSourceImpl(api)
    }
}
