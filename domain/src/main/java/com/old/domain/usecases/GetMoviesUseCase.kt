package com.old.domain.usecases

import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.old.domain.model.Movie
import com.old.domain.repository.MoviesRepository
import com.old.domainmodel.UseCase
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, GetMoviesUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Movie>>  = moviesRepository.movies(params.category)

    data class Params(val category: String)
}
