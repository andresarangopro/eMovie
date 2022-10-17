package com.old.emoviecompose.presentation.movies.listMovies

import androidx.lifecycle.ViewModel
import com.old.emoviecompose.core.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator {
    fun onStartClicked(){

    }

    fun toDetailScreen(){

    }
}

