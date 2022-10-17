package com.old.emoviecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorPalette = darkColorScheme(
    primary = Yellow80,
    onPrimary = Yellow20,
    primaryContainer = Yellow30,
    onPrimaryContainer = Yellow90,
    inversePrimary = Yellow40,
    secondary = DarkYellow80,
    onSecondary = DarkYellow20,
    secondaryContainer = DarkYellow30,
    onSecondaryContainer = DarkYellow90,
    tertiary = Orange80,
    onTertiary = Orange20,
    tertiaryContainer = Orange30,
    onTertiaryContainer = Orange90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Gray10,
    onBackground = Gray90,
    surface = Gray10,
    onSurface = Gray90,
    inverseSurface = Gray90,
    inverseOnSurface = Gray10
)

private val LightColorPalette = lightColorScheme(
    primary = Yellow40,
    onPrimary = Gray10,
    primaryContainer = Yellow90,
    onPrimaryContainer = Yellow10,
    inversePrimary = Yellow80,
    secondary = DarkYellow40,
    onSecondary = Color.White,
    secondaryContainer = DarkYellow90,
    onSecondaryContainer = DarkYellow10,
    tertiary = Orange40,
    onTertiary = Color.White,
    tertiaryContainer = Orange90,
    onTertiaryContainer = Orange10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Gray95,
    onBackground = Gray10,
    surface = Gray90,
    onSurface = Gray30,
    inverseSurface = Gray20,
    inverseOnSurface = Gray95
)

@Composable
fun EMovieComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useDynamicColors = false //Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        !useDynamicColors && darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}