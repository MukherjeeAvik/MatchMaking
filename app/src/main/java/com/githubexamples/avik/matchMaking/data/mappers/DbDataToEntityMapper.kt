package com.githubexamples.avik.matchMaking.data.mappers

import com.githubexamples.avik.matchMaking.data.entities.RandomPeopleItemDbResponse
import com.githubexamples.avik.matchMaking.data.entities.ResultsItem
import com.githubexamples.avik.matchMaking.domain.Mapper
import com.githubexamples.avik.matchMaking.domain.entitity.EachMatchCard
import javax.inject.Inject

class DbDataToEntityMapper @Inject constructor(): Mapper< RandomPeopleItemDbResponse, EachMatchCard>() {
    override fun mapFrom(from: RandomPeopleItemDbResponse): EachMatchCard {
        return EachMatchCard(
            id = from.userId,
            name = from.name,
            age = from.age,
            city = from.city,
            state = from.state,
            country = from.country,
            isInterested =  from.isInterested == 1,
            hasDeclined = from.hasDeclined == 1

        )
    }
}