package com.old.data.extensions

import com.old.emoviecompose.data.BuildConfig

object ConstantsRepositoryImp {
    val tmdbApiKey: String
        get() = "Bearer ${BuildConfig.TMDB_API_KEY}"
}