package com.example.moviesdata.usecase

import com.example.moviesdata.model.MovieDetail
import com.example.moviesdata.repository.MovieRepositoryImpl
import com.example.moviesdata.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepositoryImpl
){
    operator fun invoke(id: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getMovieDetail(id)
            emit(Resource.Success(coins))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}