package com.githubexamples.avik.matchMaking.data.mappers

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.domain.Mapper
import com.githubexamples.avik.matchMaking.utils.UNKNOWN
import javax.inject.Inject

class EntityToDbDataMapper @Inject constructor() :
    Mapper<ResultsItem, RandomPeopleItemDbResponse>() {
    override fun mapFrom(from: ResultsItem): RandomPeopleItemDbResponse {
        return RandomPeopleItemDbResponse(
            userId = from.login?.uuid ?: UNKNOWN,
            name = from.name?.first ?: "" + " " + from.name?.last ?: "",
            age = from.dob?.age?.toString() ?: "",
            city = from.location?.city ?: "",
            state = from.location?.state ?: "",
            country = from.location?.country ?: "",
            isInterested = 0,
            hasDeclined = 0

        )
    }
}