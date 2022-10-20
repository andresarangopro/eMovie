package com.old.emoviecompose.features.movies


import com.old.domain.model.Either
import com.old.domain.model.Movie
import com.old.domain.repository.MoviesRepository
import com.old.domain.usecases.GetMoviesUseCase
import com.old.emoviecompose.UnitTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesTest : UnitTest() {

    private lateinit var getMovies: GetMoviesUseCase

    @MockK
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        getMovies = GetMoviesUseCase(moviesRepository)
        every { moviesRepository.movies(mockCategory) } returns Either.Right(listOf(Movie.empty))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getMovies.run(GetMoviesUseCase.Params(mockCategory)) }

        verify(exactly = 1) { moviesRepository.movies(mockCategory)}
    }

    companion object {
        private const val mockCategory = "popular"
    }
}

