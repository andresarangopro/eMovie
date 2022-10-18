package com.old.emoviecompose.presentation.movies.listMovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.old.domain.model.Movie
import com.old.domain.usecases.GetMoviesUseCase
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.core.platform.BaseViewModel
import com.old.emoviecompose.core.platform.States
import com.old.emoviecompose.mappers.MovieView
import com.old.emoviecompose.presentation.movies.detailMovies.DetailMovieScreenRoute
import com.old.emoviecompose.presentation.movies.detailMovies.DetailMovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val getMoviesUseCase: GetMoviesUseCase
): BaseViewModel<EventsListMoviesViewModel, StatesListMoviesViewModel>(), RouteNavigator by routeNavigator, MoveScreen {

    private val _states: MutableLiveData<States<StatesListMoviesViewModel>> = MutableLiveData()
    val states: LiveData<States<StatesListMoviesViewModel>> get() = _states

    private val _moviesUpcoming: MutableLiveData<List<MovieView>> = MutableLiveData()
    val moviesUpcoming: LiveData<List<MovieView>> = _moviesUpcoming

    private val _moviesTopRated: MutableLiveData<List<MovieView>> = MutableLiveData()
    val moviesTopRated: LiveData<List<MovieView>> = _moviesTopRated

    override fun toDetailScreen(idMovie: Int){
        navigateToRoute(DetailMovieScreenRoute.get(idMovie))
    }

    fun loadTopRatedMovies() =
        getMoviesUseCase(GetMoviesUseCase.Params("top_rated"), viewModelScope) { it.fold(::handleFailure, ::handleUpcomingMovieList) }

    private fun handleUpcomingMovieList( movies: List<Movie>,) {
        _moviesUpcoming.value = movies.map { MovieView(it.id, it.poster) }
    }

    fun loadUpcomingMovies() =
        getMoviesUseCase(GetMoviesUseCase.Params("upcoming"), viewModelScope) { it.fold(::handleFailure, ::handleTopRatedMovieList) }


    private fun handleTopRatedMovieList( movies: List<Movie>,) {
        _moviesTopRated.value = movies.map { MovieView(it.id, it.poster) }
    }

    override fun manageEvent(event: EventsListMoviesViewModel) {
        when(event){
            is EventsListMoviesViewModel.GetUpcomingMovies ->{
                loadUpcomingMovies()

            }
            is EventsListMoviesViewModel.GetTopRatedMovies ->{
                loadTopRatedMovies()
            }
        }
    }
}

