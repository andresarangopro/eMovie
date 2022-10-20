package com.old.emoviecompose.presentation.movies.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.old.emoviecompose.core.navigation.INavigationComponent
import com.old.emoviecompose.presentation.movies.detailMovies.DetailMovieScreenRoute
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesRoute
import com.old.emoviecompose.presentation.movies.splashScreen.SplashScreenRoute

class NavigationMovieComponent: INavigationComponent {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun NavigationComponent(navHostController: NavHostController,
                                     paddingValues: PaddingValues
    ) {
        AnimatedNavHost(
            navController = navHostController,
            startDestination = SplashScreenRoute.route,
            modifier = Modifier.padding(paddingValues),
            exitTransition = {
                when(this.targetState.destination.route){
                    ListMoviesRoute.route->{
                        slideOutHorizontally(
                            targetOffsetX = {-300},
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = FastOutSlowInEasing
                            ))+ fadeOut(animationSpec = tween(300))

                    }
                    else -> {
                        fadeOut(animationSpec = tween(300))
                    }
                }

            },

        ) {
            ListMoviesRoute.composable(this, navHostController)
            SplashScreenRoute.composable(this, navHostController)
            DetailMovieScreenRoute.composable(
                this,
                navHostController)

        }
    }
}