package com.example.composeimdbapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeimdbapp.data.MoviesRepository
import com.example.composeimdbapp.model.Movie
import com.example.composeimdbapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MoviesRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Movie>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>>
        get() = _uiState

    fun getMoviesDetail(movieDetail: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMoviesDetail(movieDetail))
        }
    }
}