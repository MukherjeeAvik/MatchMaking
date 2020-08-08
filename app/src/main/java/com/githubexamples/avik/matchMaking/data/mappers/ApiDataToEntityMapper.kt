package com.githubexamples.avik.matchMaking.data.mappers

import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.domain.Mapper
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import com.githubexamples.avik.matchMaking.utils.UNKNOWN
import javax.inject.Inject

class ApiDataToEntityMapper @Inject constructor() : Mapper<ResultsItem, EachMatchCard>() {
    override fun mapFrom(from: ResultsItem): EachMatchCard {
        return EachMatchCard(
            id = from.login?.uuid ?: UNKNOWN,
            name = from.name?.first ?: "" + " " + from.name?.last ?: "",
            age = from.dob?.age?.toString() ?: "",
            city = from.location?.city ?: "",
            state = from.location?.state ?: "",
            country = from.location?.country ?: "",
            isInterested = false,
            hasDeclined = false


        )
    }

}