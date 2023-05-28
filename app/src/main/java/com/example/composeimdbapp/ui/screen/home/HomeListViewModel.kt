package com.example.composeimdbapp.ui.screen.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeimdbapp.data.MoviesRepository
import com.example.composeimdbapp.model.Movie
import com.example.composeimdbapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeListViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Movie>>>
        get() = _uiState


    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { movies ->
                    _uiState.value = UiState.Success(movies)
                }
        }
    }
}