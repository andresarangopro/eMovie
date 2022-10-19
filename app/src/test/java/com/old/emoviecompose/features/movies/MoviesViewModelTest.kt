package com.old.emoviecompose.features.movies

import com.old.domain.model.Either
import com.old.domain.model.Movie
import com.old.domain.usecases.GetMoviesUseCase
import com.old.emoviecompose.AndroidTest
import com.old.emoviecompose.core.navigation.RouteNavigator
import com.old.emoviecompose.presentation.movies.listMovies.ListMoviesViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest : AndroidTest() {

    private lateinit var moviesViewModel: ListMoviesViewModel

    @MockK
    private lateinit var getMovies: GetMoviesUseCase

    @MockK
    private lateinit var routeNavigator: RouteNavigator

    @Before
    fun setUp() {
        moviesViewModel = ListMoviesViewModel(routeNavigator,getMovies)
    }

    @Test
    fun `loading movies should update live data`() {
        val moviesList = listOf(Movie(0, "The Godfather"), Movie(1, "UP"))
        coEvery { getMovies.run(any()) } returns Either.Right(moviesList)

        moviesViewModel.moviesUpcoming.observeForever {
            it!!.size shouldBeEqualTo 2
            it[0].id shouldBeEqualTo 0
            it[0].poster shouldBeEqualTo "The Godfather"
            it[1].id shouldBeEqualTo 1
            it[1].poster shouldBeEqualTo "Up"
        }

        runBlocking { moviesViewModel.loadUpcomingMovies() }
    }
}