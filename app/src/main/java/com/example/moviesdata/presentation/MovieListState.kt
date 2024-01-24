package com.example.moviesdata.presentation

import com.example.moviesdata.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movie: ArrayList<Movie>? = null,
    val error: String = ""
)
