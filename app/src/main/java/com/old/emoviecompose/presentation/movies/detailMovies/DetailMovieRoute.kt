package com.old.emoviecompose.presentation.movies.detailMovies



import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.old.emoviecompose.core.navigation.KEY_CONTENT_PAGE_INDEX
import com.old.emoviecompose.core.navigation.NavRoute
import com.old.emoviecompose.core.navigation.getOrThrow
import com.old.emoviecompose.custom.component.ImageResourceWithGradient
import com.old.emoviecompose.ui.theme.EMovieComposeTheme
import com.old.emoviecompose.R


object DetailMovieScreenRoute: NavRoute<DetailMovieViewModel> {

    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    override val route = "detailMovie/{$KEY_CONTENT_PAGE_INDEX}"

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)


    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })


    @Composable
    override fun viewModel(): DetailMovieViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: DetailMovieViewModel) = ContentPage(viewModel)
}

@Composable
fun ContentPage(
    detailMovieViewModel: DetailMovieViewModel

) {
    EMovieComposeTheme() {

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Black,
                    navigationIcon = {
                        IconButton(onClick = { detailMovieViewModel.toMainScreen()}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_backarrow),
                                contentDescription = "Ir hacia atrás",
                                tint = Color.White
                            )
                        }
                    },
                    title = {

                    }
                )
            }
        ){
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
            ) {
                val movieDetail = detailMovieViewModel.movieDetails.observeAsState()
                movieDetail.value?.let {
                    Box{
                        ImageResourceWithGradient (url = it.poster,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        Box(modifier = Modifier
                            .align(Alignment.Center)
                            .blur(30.dp)
                            .height(400.dp)) {
                            Text(
                                text = it.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top =8.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center

                            )
                        }
                    }

                }
            }


        }
    }

}

