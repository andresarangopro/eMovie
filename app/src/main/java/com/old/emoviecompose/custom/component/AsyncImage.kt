package com.old.emoviecompose.custom.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.old.emoviecompose.R
import com.old.emoviecompose.core.extensions.listSizeForRecommendations
import com.old.emoviecompose.mappers.MovieView
import com.old.emoviecompose.presentation.movies.listMovies.MoveScreen

@Composable
fun ImageResource(url: String, modifier: Modifier = Modifier){
    Box(modifier= modifier){

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500$url")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop
        )

        Image(painter = painter, contentDescription = null,
            modifier=Modifier.fillMaxSize())

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

@Composable
fun IconBoxInfo(str: String, modifier: Modifier = Modifier){

    Box(   modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement= Arrangement.Start,
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.stair),
                contentDescription = null
            )
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = str.take(3),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight= FontWeight.Bold,
                    color = Color.Black,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun verticalGrid(modifier: Modifier = Modifier, movies: List<MovieView>?, moveScreen: MoveScreen){
    LazyVerticalGrid(
        modifier = Modifier.height(400.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        movies?.size?.let {
            items(it.listSizeForRecommendations()) {
                Card(modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Transparent)
                    .clickable {
                        movies?.get(it)?.id?.let { it1 -> moveScreen.toDetailScreen(it1) }
                    },
                    shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
                ) {
                    movies?.get(it)?.poster?.let {
                        ImageResource(url = it,
                            modifier= Modifier
                                .height(210.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
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

@Composable
fun ImageResourceWithGradient(url: String, modifier: Modifier = Modifier){
    Box(modifier= modifier){

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500$url")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop
        )

        Image(painter = painter, contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = size.height/3,
                    endY = size.height
                )

                onDrawWithContent {
                    drawContent()
                    drawRect(gradient,blendMode = BlendMode.Multiply)
                }
            }
           )

    }
}

