package com.old.emoviecompose.presentation.movies.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.old.emoviecompose.core.navigation.INavigationComponent
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesRoute

class NavigationMovieComponent: INavigationComponent {

    @Composable
    override fun navigationComponent(navHostController: NavHostController,
                                     paddingValues: PaddingValues
    ) {
        NavHost(
            navController = navHostController,
            startDestination = ListMoviesRoute.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            ListMoviesRoute.composable(this, navHostController)

        }
    }
}