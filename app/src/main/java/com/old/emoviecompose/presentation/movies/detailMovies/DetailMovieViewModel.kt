package com.old.emoviecompose.presentation.movies.detailMovies

import android.util.Log
import androidx.lifecycle.*
import com.old.domain.model.MovieDetails
import com.old.domain.usecases.GetMoviesDetailsUseCase
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.core.platform.BaseViewModel
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase
): BaseViewModel<EventsDetailViewModel, StatesDetailMovieViewModel>(), RouteNavigator by routeNavigator {

     private val _index = DetailMovieScreenRoute.getIndexFrom(savedStateHandle)
     val index = _index

    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    init {
        loadMovieDetails()
    }
    fun toMainScreen(){
        navigateUp()
    }



    fun loadMovieDetails() =
    getMoviesDetailsUseCase(GetMoviesDetailsUseCase.Params(index), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleMovieDetails
        )
    }

    private fun handleMovieDetails(movie: MovieDetails) {
        Log.d("movieHandle","${movie}")
        _movieDetails.value = MovieDetails(
            movie.id, movie.title, movie.poster,
            movie.summary, movie.popularity, movie.release_date, movie.genres
        )
    }

    override fun manageEvent(event: EventsDetailViewModel) {
        TODO("Not yet implemented")
    }

}

