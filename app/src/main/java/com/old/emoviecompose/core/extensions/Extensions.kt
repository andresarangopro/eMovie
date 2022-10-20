package com.old.emoviecompose.core.extensions

fun String.getYearOfReleaseDate() = this.split("-")[0]

fun Int.listSizeForRecommendations(): Int = if(this > 6)  6 else 0

