package com.old.emoviecompose.features.movies

import com.old.domain.model.Either
import com.old.domain.model.MovieDetails
import com.old.domain.repository.MoviesRepository
import com.old.domain.usecases.GetMoviesDetailsUseCase
import com.old.domain.usecases.GetMoviesUseCase
import com.old.emoviecompose.UnitTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMovieDetailsTest : UnitTest() {

    private lateinit var getMovieDetails: GetMoviesDetailsUseCase

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovieDetails = GetMoviesDetailsUseCase(moviesRepository)
        every { moviesRepository.movieDetails(MOVIE_ID) } returns Either.Right(MovieDetails.empty)
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovieDetails.run(GetMoviesDetailsUseCase.Params(MOVIE_ID)) }

        verify(exactly = 1) { moviesRepository.movieDetails(MOVIE_ID) }
    }

    companion object {
        private const val MOVIE_ID = 1
    }
}
