package com.githubexamples.avik.matchMaking.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.githubexamples.avik.matchMaking.utils.MATCH_MAKING_TABLE


@Entity(tableName = MATCH_MAKING_TABLE)
data class RandomPeopleItemDbResponse(
    @PrimaryKey
    val userId:String,
    val name: String,
    val age: String,
    val city: String,
    val state: String,
    val country: String,
    val isInterested:Int,
    val hasDeclined:Int

)