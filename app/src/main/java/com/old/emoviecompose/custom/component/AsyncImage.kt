package com.old.emoviecompose.custom.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

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

