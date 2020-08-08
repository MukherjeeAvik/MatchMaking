package com.githubexamples.avik.matchMaking.data.api.utils

class NoInternetAvailableException : Exception() {
    
    override val message: String?
        get() = "No internet available"

}
