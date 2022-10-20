package com.old.emoviecompose.presentation.movies.splashScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.old.emoviecompose.core.navigation.NavRoute
import com.old.emoviecompose.R
import kotlinx.coroutines.delay

object SplashScreenRoute: NavRoute<SplashViewModel> {

    override val route = "splash/"

    @Composable
    override fun viewModel(): SplashViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: SplashViewModel) = ContentPage(viewModel)
}

@Composable
fun ContentPage(
    splashViewModel: SplashViewModel
) {
    LaunchedEffect(key1 = true){
        delay(3000)
        splashViewModel.toMainScreen()
    }
    Splash()

}


@Composable
fun Splash(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        MaterialTheme.colorScheme.inversePrimary

                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.emovie),
            contentDescription = "logo Emovie"
        )
    }
}

