package com.old.emoviecompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.old.emoviecompose.R


val fonts = FontFamily(
    Font(R.font.poppinsmedium, weight = FontWeight.Bold),
    Font(R.font.poppinsregular, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = androidx.compose.material3.Typography(
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )

)

