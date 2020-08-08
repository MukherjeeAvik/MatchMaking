package com.githubexamples.avik.matchMaking.domain

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T
}