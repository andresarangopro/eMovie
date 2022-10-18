package com.old.emoviecompose.presentation.movies.listMovies


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.old.emoviecompose.core.navigation.NavRoute
import com.old.emoviecompose.mappers.MovieView
import com.old.emoviecompose.ui.theme.EMovieComposeTheme

import  com.old.emoviecompose.R
import com.old.emoviecompose.custom.component.ImageResource

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
                verticalGrid(movies = moviesTopRated.value, moveScreen = listMoviesViewModel)
            }



        }
    }
}

interface MoveScreen{
    fun toDetailScreen(idMovie: Int)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyRowItemsDemo(
    modifier: Modifier = Modifier,
    movies: List<MovieView>?,
    moveScreen: MoveScreen,

    ) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(movies?.size ?: 0,itemContent = { item ->
            Card(modifier = Modifier
                .padding(8.dp)
                .clickable {
                    movies?.get(item)?.id?.let { moveScreen.toDetailScreen(it) }
                },
                shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
                ) {
                movies?.get(item)?.poster?.let {
                    ImageResource(url = it,
                        Modifier
                            .height(150.dp)
                            .width(100.dp))
                }
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun verticalGrid(modifier: Modifier = Modifier,movies: List<MovieView>?, moveScreen: MoveScreen){
    LazyVerticalGrid(
        modifier = Modifier.height(400.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        ) {
        items(listSize(movies?.size ?: 0)) {
            Card(modifier = Modifier
                .padding(8.dp)
                .clickable {
                    movies?.get(it)?.id?.let { it1 -> moveScreen.toDetailScreen(it1) }
                },shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp)) {
                movies?.get(it)?.poster?.let {
                    ImageResource(url = it,
                        modifier= Modifier
                        .height(210.dp)
                        .fillMaxWidth())
                }
            }
        }
    }
}

fun listSize(size: Int):Int{
     if(size > 6) return 6
    return size
}

@Composable
fun Title(str:String){
    Text(
        text = str,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        color = colorResource(id = R.color.white),
        modifier = Modifier
            .padding(8.dp, 8.dp, 0.dp, 0.dp)
            .fillMaxWidth()
    )
}


