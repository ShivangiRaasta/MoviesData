package com.example.moviesdata.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesdata.model.Movie
import com.example.moviesdata.model.MovieDetail
import com.example.moviesdata.paging.PopularMoviePagingSource
import com.example.moviesdata.paging.RatedMoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//Implementing the MovieRepository
class MovieRepositoryImpl @Inject constructor(
    //Private Immutable variable api that inherits from MovieApi
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { PopularMoviePagingSource(api) }
        ).flow
    }

    override suspend fun getRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { RatedMoviePagingSource(api) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun getMovieDetail(id: String): MovieDetail {
        return api.getMovieDetail(id)
    }
}