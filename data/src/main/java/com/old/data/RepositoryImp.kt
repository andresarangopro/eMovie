package com.old.data


import com.old.data.extensions.ConstantsRepositoryImp.tmdbApiKey
import com.old.data.platform.NetworkHandler
import com.old.domain.databasemanager.ILocalDataSource
import com.old.domain.databasemanager.MovieDao
import com.old.domain.databasemanager.MovieDetailDao
import com.old.domain.databasemanager.localDatabaseEntities.MovieEntity
import com.old.domain.entities.MovieDetailsEntity
import com.old.domain.entities.ResultsEntity
import com.old.domain.entities.TrailerEntity
import com.old.domain.model.*
import com.old.domain.repository.*
import com.old.data.extensions.request
import com.old.domain.databasemanager.TrailerDao
import com.old.domain.databasemanager.localDatabaseEntities.MovieDetailEntity
import com.old.domain.databasemanager.localDatabaseEntities.MovieWithGenresEntity

import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class RemoteDataSource
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun movies(authHeader: String, category: String): Call<ResultsEntity> =
        moviesApi.movies(authHeader = authHeader, category = category)


    override fun movieDetails(authHeader: String, movieId: Int): Call<MovieDetailsEntity> =
        moviesApi.movieDetails(authHeader = authHeader, movieId = movieId)

    override fun movieVideos(authHeader: String, movieId: Int): Call<TrailerEntity.TrailerResultsEntity> =
        moviesApi.movieVideos(authHeader = authHeader, movieId= movieId)

}


@Singleton
class LocalMovieDataSource
@Inject constructor(
    private val movieDao: MovieDao,
    private val movieDetailDao: MovieDetailDao,
    private val trailerDao: TrailerDao
    ) : ILocalDataSource {
    override fun getListMovies(category: String): List<MovieEntity> {
        return movieDao.getMoviesByCategory(category)
    }

    override fun createMovies(listMovies: List<MovieEntity>) {
        return movieDao.insertMovieList(listMovies)
    }

    override fun getDetailMovie(movieId: Int): MovieDetailEntity {
        return movieDetailDao.getMovieById(movieId)
    }

    override fun getDetailMovieWithGenres(movieId: Int): MovieWithGenresEntity {
        return movieDetailDao.getByMovieId(movieId)
    }

    override fun createMovieDetail(movieDetail: MovieDetails) {
        movieDetailDao.insertMovie(movieDetail.toMovieDatabaseEntity())
        movieDetail.genres.forEach {
            movieDetailDao.insertGenre(it.toGenreDatabaseEntity(movieDetail.id))
        }
    }

    override fun getTrailerByMovieId(movieId: Int): com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity {
       return trailerDao.getTrailerById(movieId)
    }

    override fun createTrailer(trailerEntity: com.old.domain.databasemanager.localDatabaseEntities.TrailerEntity) {
        trailerDao.insertTrailer(trailerEntity)
    }

}

class RepositoryImp
@Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: RemoteDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MoviesRepository {

    override fun movies(category: String): Either<Failure, List<Movie>> {
        var request: Either<Failure, List<Movie>>
        when (networkHandler.isNetworkAvailable()) {
            true -> {
                request = request(
                    service.movies(tmdbApiKey, category),
                    { it.toMoviesList() },
                    ResultsEntity(emptyList())
                )
            }
            false -> request= getLocalMovies(category)
        }

        request.fold(
             fnL = {},
             fnR = {localMovieDataSource.createMovies(it.toMoviesDatabaseEntity(category))}
        )
        return request
    }

    private fun getLocalMovies(category: String): Either<Failure, List<Movie>> {

        return try {
            val response = localMovieDataSource.getListMovies(category)
            when (response.isNotEmpty()) {
                true -> Either.Right(response.toMovieList())
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }


    override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
        var request: Either<Failure, MovieDetails>
        when (networkHandler.isNetworkAvailable()) {
            true -> {
                request = request(
                    service.movieDetails(tmdbApiKey, movieId),
                    { it.toMovieDetails() },
                    MovieDetailsEntity.empty
                )
            }
            false -> request = getLocalMovieDetail(movieId)
        }
        request.fold(
            fnL = {},
            fnR = {localMovieDataSource.createMovieDetail(it)}
        )
        return  request
    }

    private fun  getLocalMovieDetail(movieId:Int): Either<Failure, MovieDetails> {
        return try {
            val response = localMovieDataSource.getDetailMovieWithGenres(movieId)
            when (response != null) {
                true -> Either.Right(response.toMovieDetails())
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }


    override fun movieTrailer(movieId: Int): Either<Failure, List<Trailer>> {
        var request: Either<Failure, List<Trailer>>
        when (networkHandler.isNetworkAvailable()) {
            true -> {
                request = request(
                    service.movieVideos(tmdbApiKey,movieId),
                    { it.toTrailerList() },
                    TrailerEntity.TrailerResultsEntity(emptyList())
                )
            }
            false -> request = getLocalTrailer(movieId)
        }

        request.fold(
            fnL = {},
            fnR = {localMovieDataSource.createTrailer(it.toTrailerDatabaseEntity(movieId))}
        )

        return request
    }

    private fun getLocalTrailer(movieId:Int): Either<Failure, List<Trailer>> {
        return try {
            val response = localMovieDataSource.getTrailerByMovieId(movieId)
            when (response != null) {
                true -> Either.Right(listOf(response.toTrailerDomain()))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }

}


