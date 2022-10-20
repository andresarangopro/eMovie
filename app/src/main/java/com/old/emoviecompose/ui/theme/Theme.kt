package com.old.emoviecompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext


private val DarkColorPalette = darkColorScheme(
    primary = Transparent,
    onPrimary =Transparent,
    primaryContainer = Yellow30,
    onPrimaryContainer = Yellow90,
    inversePrimary = Red1,
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
    surface = Black,
    onSurface = Gray90,
    inverseSurface = Gray90,
    inverseOnSurface = Gray10
)

private val LightColorPalette = lightColorScheme(
    primary = Transparent,
    onPrimary = White,
    primaryContainer = Yellow90,
    onPrimaryContainer = White,
    inversePrimary = Red1,
    secondary = White,
    onSecondary =Black,
    secondaryContainer = DarkYellow90,
    onSecondaryContainer = DarkYellow10,
    tertiary = Orange40,
    onTertiary = Gray10,
    tertiaryContainer = Orange90,
    onTertiaryContainer = Orange10,
    error = Red40,
    onError = Gray10,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Gray10,
    onBackground = Gray10,
    surface = Black,
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