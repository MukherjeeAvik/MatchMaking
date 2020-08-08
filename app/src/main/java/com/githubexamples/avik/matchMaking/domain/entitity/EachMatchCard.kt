package com.githubexamples.avik.matchMaking.domain.entitity

data class EachMatchCard(
    val id:String,
    val name: String,
    val age: String,
    val city: String,
    val state: String,
    val country: String,
    val isInterested:Boolean,
    val hasDeclined:Boolean
)