package com.example.moviesdata.model

data class MoviesDto (
    val results: ArrayList<Movie>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)
