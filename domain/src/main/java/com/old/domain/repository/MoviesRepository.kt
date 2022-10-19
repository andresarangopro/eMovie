package com.old.domain.repository


import com.old.domain.model.*


interface MoviesRepository {
    fun movies( category: String): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>
    fun movieTrailer(movieId: Int): Either<Failure, List<Trailer>>

}