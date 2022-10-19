package com.old.emoviecompose.presentation.movies.listMovies


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.old.emoviecompose.core.navigation.NavRoute
import com.old.emoviecompose.ui.theme.EMovieComposeTheme
import  com.old.emoviecompose.R
import com.old.emoviecompose.custom.component.LazyRowItemsDemo
import com.old.emoviecompose.custom.component.Title
import com.old.emoviecompose.custom.component.verticalGrid

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
    EMovieComposeTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            listMoviesViewModel.postEvent(EventsListMoviesViewModel.GetTopRatedMovies)
            listMoviesViewModel.postEvent(EventsListMoviesViewModel.GetUpcomingMovies)
            val moviesUpComing= listMoviesViewModel.moviesUpcoming.observeAsState()
            val moviesTopRated= listMoviesViewModel.moviesTopRated.observeAsState()

            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())) {

                Image(
                    painter = painterResource(id = R.drawable.emovie),
                    modifier = Modifier
                        .padding(0.dp, 24.dp, 0.dp, 0.dp)
                        .fillMaxWidth()
                        .size(15.dp)
                    ,
                    contentDescription = "logo Emovie"

                )
                Title(str = stringResource(id = R.string.upcoming))

                Spacer(modifier = Modifier.height(8.dp))
                LazyRowItemsDemo(
                    movies = moviesUpComing.value,
                    moveScreen = listMoviesViewModel
                )

                Spacer(modifier = Modifier.height(16.dp))

                Title(str = stringResource(id = R.string.top_rated))
                Spacer(modifier = Modifier.height(8.dp))
                LazyRowItemsDemo(movies = moviesTopRated.value,
                    moveScreen = listMoviesViewModel)

                Spacer(modifier = Modifier.height(16.dp))
                Title(str = stringResource(id = R.string.trend))
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier
                    .width(250.dp)
                    .padding(10.dp, 0.dp)
                    .align(Alignment.Start),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    ButtonFilter(str = "Idioma", Modifier.weight(0.5f)
                        .border(width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(30.dp)))
                    Spacer(Modifier.weight(0.1f))
                    ButtonFilter(str = "Año", Modifier.weight(0.5f)
                        .border(width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(30.dp)))
                }
                verticalGrid(movies = moviesTopRated.value, moveScreen = listMoviesViewModel)
            }
        }
    }
}

@Composable
fun ButtonFilter(str: String, modifier: Modifier=Modifier){
    Button(onClick = { /*TODO*/ },
        modifier = modifier
    ){
        Text(text = str,
            color=Color.White)
    }
}

interface MoveScreen{
    fun toDetailScreen(idMovie: Int)
}

