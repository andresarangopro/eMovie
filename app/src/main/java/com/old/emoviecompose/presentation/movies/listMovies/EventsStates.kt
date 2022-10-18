package com.old.emoviecompose.presentation.movies.listMovies

import android.content.Intent
import com.old.domain.model.Movie
import com.old.emoviecompose.mappers.MovieView

sealed class EventsListMoviesViewModel {
    object GetUpcomingMovies:EventsListMoviesViewModel()
    object GetTopRatedMovies:EventsListMoviesViewModel()
}

sealed class StatesListMoviesViewModel {
    data class OnLoadedMovies(val movies: List<Movie>): StatesListMoviesViewModel()
}
