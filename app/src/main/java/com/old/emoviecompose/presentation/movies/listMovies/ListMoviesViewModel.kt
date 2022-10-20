package com.old.emoviecompose.presentation.movies.listMovies


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.old.domain.model.Movie
import com.old.domain.usecases.GetMoviesUseCase
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.core.platform.BaseViewModel
import com.old.emoviecompose.core.platform.States
import com.old.emoviecompose.presentation.movies.detailMovies.DetailMovieScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val getMoviesUseCase: GetMoviesUseCase
): BaseViewModel<EventsListMoviesViewModel, StatesListMoviesViewModel>(), RouteNavigator by routeNavigator, MoveScreen {

    private val _states: MutableLiveData<States<StatesListMoviesViewModel>> = MutableLiveData()
    val states: LiveData<States<StatesListMoviesViewModel>> get() = _states

    private val _moviesUpcoming: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesUpcoming: LiveData<List<Movie>> = _moviesUpcoming

    private val _moviesTopRated: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesTopRated: LiveData<List<Movie>> = _moviesTopRated

    override fun toDetailScreen(idMovie: Int){
        navigateToRoute(DetailMovieScreenRoute.get(idMovie))
    }

    private fun loadTopRatedMovies() =
        getMoviesUseCase(GetMoviesUseCase.Params("top_rated"), viewModelScope) { it.fold(::handleFailure, ::handleTopRatedMovieList) }

    private fun handleUpcomingMovieList( movies: List<Movie>) {
        _moviesUpcoming.value = movies
    }

    fun loadUpcomingMovies() =
        getMoviesUseCase(GetMoviesUseCase.Params("upcoming"), viewModelScope) { it.fold(::handleFailure, ::handleUpcomingMovieList) }


    private fun handleTopRatedMovieList( movies: List<Movie>) {
        _moviesTopRated.value = movies
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

