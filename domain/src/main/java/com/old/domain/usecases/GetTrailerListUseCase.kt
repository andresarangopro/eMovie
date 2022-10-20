package com.old.domain.usecases

import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.old.domain.model.Trailer
import com.old.domain.repository.MoviesRepository
import com.old.domain.model.UseCase
import javax.inject.Inject

class GetTrailerListUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Trailer>, GetTrailerListUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Trailer>>  = moviesRepository.movieTrailer(params.movieId)

    data class Params(val movieId: Int)
}
