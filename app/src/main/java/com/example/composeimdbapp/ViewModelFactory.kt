package com.example.composeimdbapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeimdbapp.data.MoviesRepository
import com.example.composeimdbapp.ui.screen.detail.DetailViewModel
import com.example.composeimdbapp.ui.screen.home.HomeListViewModel

class ViewModelFactory(private val repository: MoviesRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeListViewModel::class.java)) {
            return HomeListViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}