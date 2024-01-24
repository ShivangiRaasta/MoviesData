package com.example.moviesdata.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviesdata.model.Movie
import com.example.moviesdata.usecase.GetPopularMovieUseCase
import com.example.moviesdata.usecase.GetRatedMovieUseCase
import com.example.moviesdata.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getRatedMovieUseCase: GetRatedMovieUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state
    private var popularData = MutableLiveData<PagingData<Movie>>()
    val readPopularData: LiveData<PagingData<Movie>> get() = popularData
    private var ratedData = MutableLiveData<PagingData<Movie>>()
    val readRatedData: LiveData<PagingData<Movie>> get() = ratedData

    init {
        getPopularMovie()
    }

    fun getPopularMovie() {
        getPopularMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                   // _state.value = MovieListState(movie = result.data ?: null)
                    popularData.value = result.data!!
                }

                is Resource.Error -> {
                    _state.value = MovieListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRatedMovie() {
        getRatedMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    ratedData.value = result.data!!
                }

                is Resource.Error -> {
                    _state.value = MovieListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}