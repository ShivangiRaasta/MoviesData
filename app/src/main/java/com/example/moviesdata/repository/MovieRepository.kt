package com.example.moviesdata.repository

import androidx.paging.PagingData
import com.example.moviesdata.model.Movie
import com.example.moviesdata.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<PagingData<Movie>>

    suspend fun getRatedMovies(): Flow<PagingData<Movie>>

    suspend fun getMovieDetail(id: String): MovieDetail

}