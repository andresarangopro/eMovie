package com.old.data


import com.old.data.extensions.ConstantsRepositoryImp.tmdbApiKey
import com.old.data.platform.NetworkHandler
import com.old.domain.entities.MovieDetailsEntity
import com.old.domain.entities.MovieEntity
import com.old.domain.entities.ResultsEntity
import com.old.domain.model.Either
import com.old.domain.model.Failure
import com.old.domain.model.Movie
import com.old.domain.model.MovieDetails
import com.old.domain.repository.MoviesRepository
import com.old.domain.repository.MoviesApi
import com.old.domain.repository.toMoviesList

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class MovieRepositoryImp
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun movies(authHeader: String, category: String): Call<ResultsEntity> =
        moviesApi.movies(authHeader = authHeader, category = category)


    override fun movieDetails(authHeader: String, movieId: Int): Call<MovieDetailsEntity> =
        moviesApi.movieDetails(authHeader = authHeader, movieId = movieId)
}

class Network
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: MovieRepositoryImp
) : MoviesRepository {

    override fun movies(category: String): Either<Failure, List<Movie>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                service.movies(tmdbApiKey,category),
                { it.toMoviesList()},
                ResultsEntity(emptyList())
            )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                service.movieDetails(tmdbApiKey,movieId),
                { it.toMovieDetails() },
                MovieDetailsEntity.empty
            )
            false -> Either.Left(Failure.NetworkConnection)//TODO(when false it should return located cache)
        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((((response.body() ?: default)))))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}


