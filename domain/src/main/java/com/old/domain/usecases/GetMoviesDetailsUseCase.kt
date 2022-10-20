package com.old.domain.usecases

import com.old.domain.model.MovieDetails
import com.old.domain.repository.MoviesRepository
import com.old.domain.model.UseCase
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepository):
    UseCase<MovieDetails, GetMoviesDetailsUseCase.Params>(){

    override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

    data class Params(val id: Int)
}