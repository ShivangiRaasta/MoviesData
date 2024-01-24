package com.example.moviesdata.model

//MovieDetail data class
data class MovieDetail (
    val id: Int,
    val original_title: String,
    val overview: String,
    val backdrop_path: String,
    val title: String,
    val genres: ArrayList<Genres>,
    val runtime: Int
)

data class Genres (
    val id: Int,
    val name: String,
)
