package com.old.emoviecompose.presentation.movies.splashScreen

import androidx.lifecycle.ViewModel
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
): ViewModel(), RouteNavigator by routeNavigator {

    fun toMainScreen(){
        popBakStackToRoute(ListMoviesRoute.route)
    }

}

