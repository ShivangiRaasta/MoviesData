package com.example.moviesdata.repository

import com.example.moviesdata.model.MovieDetail
import com.example.moviesdata.model.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNTU4NjU0YTRmZDBlOWRmMTdmNzA5Yzg2OWE0ZTE5NSIsInN1YiI6IjY1YWExMmQ5NTM0NjYxMDEyOGNkMjk1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qGbhPeoxEyzvAovvxUH-17mZ80bdvlxnqqHADzI0c3k")
    @GET("popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesDto

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNTU4NjU0YTRmZDBlOWRmMTdmNzA5Yzg2OWE0ZTE5NSIsInN1YiI6IjY1YWExMmQ5NTM0NjYxMDEyOGNkMjk1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qGbhPeoxEyzvAovvxUH-17mZ80bdvlxnqqHADzI0c3k")
    @GET("top_rated")
    suspend fun getRatedMovies(@Query("page") page: Int): MoviesDto

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNTU4NjU0YTRmZDBlOWRmMTdmNzA5Yzg2OWE0ZTE5NSIsInN1YiI6IjY1YWExMmQ5NTM0NjYxMDEyOGNkMjk1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qGbhPeoxEyzvAovvxUH-17mZ80bdvlxnqqHADzI0c3k")
    @GET("{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: String): MovieDetail
}