package com.old.emoviecompose.presentation.movies.listMovies

import com.old.domain.model.Movie

sealed class EventsListMoviesViewModel {
    object GetUpcomingMovies:EventsListMoviesViewModel()
    object GetTopRatedMovies:EventsListMoviesViewModel()
}

sealed class StatesListMoviesViewModel {
    data class OnLoadedMovies(val movies: List<Movie>): StatesListMoviesViewModel()
}
