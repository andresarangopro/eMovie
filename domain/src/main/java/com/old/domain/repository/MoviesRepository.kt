package com.old.domain.repository


import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.old.domain.model.Movie
import com.old.domain.model.MovieDetails


interface MoviesRepository {
    fun movies( category: String): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>

}