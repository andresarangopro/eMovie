package com.old.emoviecompose.presentation.movies.listMovies


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.old.emoviecompose.core.navigation.NavRoute


object ListMoviesRoute : NavRoute<ListMoviesViewModel> {

    override val route = "listMovies/"

    @Composable
    override fun viewModel(): ListMoviesViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ListMoviesViewModel) = ContentPage(viewModel)
}

@Composable
fun ContentPage(
    listMoviesViewModel: ListMoviesViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Text(
            text ="Hi",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
    }
}