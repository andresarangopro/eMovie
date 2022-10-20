package com.old.domain.entities


data class MovieEntity(val id: Int, val poster_path: String)

data class ResultsEntity(val results: List<MovieEntity>)