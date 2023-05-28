package com.example.composeimdbapp.di

import com.example.composeimdbapp.data.MoviesRepository

object Injection {
    fun provideRepository(): MoviesRepository {
        return MoviesRepository.getInstance()
    }
}