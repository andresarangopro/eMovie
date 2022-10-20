package com.old.emoviecompose.presentation.movies.detailMovies

import android.util.Log
import androidx.lifecycle.*
import com.old.domain.model.MovieDetails
import com.old.domain.model.Trailer
import com.old.domain.usecases.GetMoviesDetailsUseCase
import com.old.domain.usecases.GetTrailerListUseCase
import com.old.domain.usecases.PlayTrailerUseCase
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.core.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    private val getTrailerListUseCase: GetTrailerListUseCase,
    private val playTrailerUseCase: PlayTrailerUseCase
): BaseViewModel<EventsDetailViewModel, StatesDetailMovieViewModel>(), RouteNavigator by routeNavigator {

     private val _index:Int = DetailMovieScreenRoute.getIndexFrom(savedStateHandle)
     val index = _index

    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _movieTrailers: MutableLiveData<List<Trailer>> = MutableLiveData()
    val movieTrailers: LiveData<List<Trailer>> = _movieTrailers

    init {
        loadMovieDetails(index)
        loadMovieTrailers(index)
    }
    fun toMainScreen(){
        navigateUp()
    }

    fun playTrailer() = playTrailerUseCase(PlayTrailerUseCase.Params(getTrailer().key), viewModelScope)

    fun loadMovieDetails(movieId: Int) =
    getMoviesDetailsUseCase(GetMoviesDetailsUseCase.Params(movieId), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleMovieDetails
        )
    }

    fun loadMovieTrailers(movieId: Int) =
    getTrailerListUseCase(GetTrailerListUseCase.Params(index), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleTrailers
        )
    }

    private fun handleMovieDetails(movie: MovieDetails) {
        Log.d("movieHandle","${movie}")
        _movieDetails.value = MovieDetails(
            movie.id, movie.title, movie.poster,
            movie.summary, movie.popularity, movie.release_date,movie.vote_average, movie.genres
        )
    }

    fun getLinkTrailer(): String{
        var trailer = getTrailer()
        if(trailer.site == "YouTube"){
            return "https://www.youtube.com/watch?v=${trailer.key}"
        }
        return ""
    }

    fun getTrailer(): Trailer {
        if(movieTrailers.value?.size!! > 0){
            return movieTrailers.value!![0]
        }
        return Trailer.empty
    }

    private fun handleTrailers(listTrailers: List<Trailer>) {
        listTrailers.forEach {
            Log.d("trailer","${it.type} ${it.site} ${it.key}")
        }
        _movieTrailers.value = listTrailers
    }

    override fun manageEvent(event: EventsDetailViewModel) {
        TODO("Not yet implemented")
    }

}

