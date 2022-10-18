package com.old.emoviecompose.presentation.movies.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.old.emoviecompose.core.navigation.INavigationComponent
import com.old.emoviecompose.presentation.movies.detailMovies.DetailMovieScreenRoute
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesRoute
import com.old.emoviecompose.presentation.movies.splashScreen.SplashScreenRoute

class NavigationMovieComponent: INavigationComponent {

    @Composable
    override fun navigationComponent(navHostController: NavHostController,
                                     paddingValues: PaddingValues
    ) {
        NavHost(
            navController = navHostController,
            startDestination = SplashScreenRoute.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            ListMoviesRoute.composable(this, navHostController)
            SplashScreenRoute.composable(this, navHostController)
            DetailMovieScreenRoute.composable(
                this,
                navHostController)

        }
    }
}