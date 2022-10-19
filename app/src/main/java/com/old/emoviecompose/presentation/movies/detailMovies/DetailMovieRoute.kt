package com.old.emoviecompose.presentation.movies.detailMovies




import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.old.emoviecompose.ui.theme.Gray50
import com.old.emoviecompose.ui.theme.Yellow50


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
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
            ) {
                val movieDetail = detailMovieViewModel.movieDetails.observeAsState()
                movieDetail.value?.let {
                    Box {
                        ImageResourceWithGradient(
                            url = it.poster,
                            modifier = Modifier
                                .fillMaxSize()

                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(0.dp, 310.dp, 0.dp, 0.dp)
                                    .verticalScroll(rememberScrollState())
                                    .weight(6f)
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = it.title,
                                        modifier = Modifier
                                            .fillMaxWidth() ,
                                        color = Color.White,
                                        style = MaterialTheme.typography.titleLarge,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 8.dp
                                        ),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    BoxInfo("2013",modifier = Modifier
                                        .padding(
                                            10.dp, 0.dp
                                        )
                                        .size(70.dp, 40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Gray50))
                                    BoxInfo("en",
                                        modifier = Modifier
                                            .padding(
                                                10.dp, 0.dp
                                            )
                                            .size(40.dp, 40.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Gray50))
                                    BoxInfo("en",
                                        modifier = Modifier
                                            .padding(
                                                10.dp, 0.dp
                                            )
                                            .size(100.dp, 40.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(Yellow50))

                                }
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 8.dp
                                        ),
                                    horizontalArrangement = Arrangement.Center) {
                                    it.genres.forEachIndexed{  index, genre ->
                                        Text(
                                            modifier = Modifier.padding(
                                                5.dp, 0.dp
                                            ),
                                            text = "${genre.name} ${if(index+1 < it.genres.size)"•" else ""}" ,
                                            color = Color.White,
                                        )
                                    }

                                }
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 8.dp,
                                            top = 8.dp
                                        )) {
                                    Button(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .border( width = 2.dp,
                                                color = Color.White,
                                                shape = RoundedCornerShape(5.dp)),

                                        ) {
                                        Text(
                                            text = "Ver Trailer",
                                            modifier = Modifier
                                                .weight(1f)
                                                .background(Color.Transparent),
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                        )
                                    }
                                }

                            }

                            Column(
                                modifier = Modifier
                                    .verticalScroll(rememberScrollState())
                                    .weight(1f)
                            ) {
                                Text(
                                    text = "Movie Plot",
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                )
                                Text(
                                    text = it.summary,
                                    textAlign = TextAlign.Left,
                                    color = Color.White,
                                )
                            }
                        }

                    }


                }
            }
        }
    }

}
@Composable
fun BoxInfo(str: String, modifier: Modifier = Modifier){
    Box(
        contentAlignment = Alignment.Center,
        modifier =modifier){
        Text(
            text = str,
            fontSize= 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
        )
    }

}
