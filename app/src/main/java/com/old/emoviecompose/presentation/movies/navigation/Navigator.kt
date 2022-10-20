package com.old.emoviecompose.presentation.movies.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.old.domain.empty
import com.old.domain.usecases.INavigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(): INavigator {

    private val videoUrlHttp = "http://www.youtube.com/watch?v="
    private val videoUrlHttps = "https://www.youtube.com/watch?v="

    override fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(videoUrlHttp) -> videoUrl.replace(videoUrlHttp, String.empty())
            videoUrl.startsWith(videoUrlHttps) -> videoUrl.replace(
                videoUrlHttps,
                String.empty()
            )
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return intent
    }
}


