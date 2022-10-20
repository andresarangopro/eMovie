package com.old.emoviecompose.presentation.movies.detailMovies




import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.old.emoviecompose.core.extensions.getYearOfReleaseDate
import com.old.emoviecompose.custom.component.BoxInfo
import com.old.emoviecompose.custom.component.IconBoxInfo
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentPage(
    detailMovieViewModel: DetailMovieViewModel

) {
    EMovieComposeTheme{
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { detailMovieViewModel.toMainScreen()}) {
                            Icon(
                                painter = painterResource(R.drawable.ic_backarrow),
                                contentDescription = stringResource(id = R.string.goBack),
                                tint = Color.White
                            )
                        }
                    },
                    title = {}
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
                                    .weight(1f),
                                verticalArrangement = Arrangement.Bottom
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
                                    BoxInfo(it.release_date.getYearOfReleaseDate(),modifier = Modifier
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
                                    IconBoxInfo(it.vote_average,
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
                                        onClick = {detailMovieViewModel.playTrailer()
                                        },
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
                                    .padding(10.dp, 10.dp)
                                    .weight(0.2f)
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
