package com.old.emoviecompose.presentation.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.old.emoviecompose.presentation.movies.navigation.NavigationMovieComponent
import com.old.emoviecompose.ui.theme.EMovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint


interface ActivityActions{
    fun openActivity(activityClass: Class<MoviesActivity>)
}

@AndroidEntryPoint
class MoviesActivity : ComponentActivity(), ActivityActions  {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            EMovieComposeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold {
                    NavigationMovieComponent().NavigationComponent(
                        navController, it
                    )
                }
            }
        }
    }

    override fun openActivity(activityClass: Class<MoviesActivity>) {
        startActivity(Intent(this, activityClass))
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

}
