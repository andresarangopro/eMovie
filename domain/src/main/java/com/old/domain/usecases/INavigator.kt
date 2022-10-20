package com.old.domain.usecases

import android.content.Context

interface INavigator {
    fun openVideo(context: Context, videoUrl: String)
}